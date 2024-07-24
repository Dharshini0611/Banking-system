package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

@WebServlet("/WithdrawServlet")
public class WithdrawServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        double amount = Double.parseDouble(request.getParameter("amount"));
        String accountNumber = (String) request.getSession().getAttribute("username");

        Connection conn = null;
        PreparedStatement pst = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "1234");

            // Check if balance is sufficient
            String checkBalance = "SELECT initial_balance FROM customers WHERE account_number = ?";
            pst = conn.prepareStatement(checkBalance);
            pst.setString(1, accountNumber);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                double currentBalance = rs.getDouble("initial_balance");

                if (currentBalance >= amount) {
                    // Update balance
                    String updateBalance = "UPDATE customers SET initial_balance = initial_balance - ? WHERE account_number = ?";
                    pst = conn.prepareStatement(updateBalance);
                    pst.setDouble(1, amount);
                    pst.setString(2, accountNumber);
                    int row = pst.executeUpdate();

                    if (row > 0) {
                        // Insert transaction record
                        String insertTransaction = "INSERT INTO transactions (account_number, amount, transaction_type) VALUES (?, ?, 'Withdraw')";
                        pst = conn.prepareStatement(insertTransaction);
                        pst.setString(1, accountNumber);
                        pst.setDouble(2, amount);
                        pst.executeUpdate();

                        // Set attributes for success message and current balance
                        request.setAttribute("message", "Withdrawal successful.");
                        request.setAttribute("currentBalance", currentBalance - amount);

                        // Redirect to withdraw_result.jsp
                        request.getRequestDispatcher("withdraw_result.jsp").forward(request, response);
                    } else {
                        out.println("Error in withdrawal.");
                    }
                } else {
                    out.println("Insufficient balance.");
                }
            } else {
                out.println("Account not found.");
            }
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                out.println("Error in closing database resources: " + ex.getMessage());
            }
            out.close();
        }
    }
}
