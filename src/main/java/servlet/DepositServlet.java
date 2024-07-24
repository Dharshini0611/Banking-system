package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

@WebServlet("/DepositServlet")
public class DepositServlet extends HttpServlet {
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

            // Update balance
            String updateBalance = "UPDATE customers SET initial_balance = initial_balance + ? WHERE account_number = ?";
            pst = conn.prepareStatement(updateBalance);
            pst.setDouble(1, amount);
            pst.setString(2, accountNumber);
            int row = pst.executeUpdate();

            if (row > 0) {
                // Fetch current balance after update
                double currentBalance = getCurrentBalance(conn, accountNumber);

                // Set current balance in request attribute
                request.setAttribute("currentBalance", currentBalance);

                // Insert transaction record
                insertTransaction(conn, accountNumber, "Deposit", amount);

                // Forward to deposit_result.jsp
                request.getRequestDispatcher("deposit_result.jsp").forward(request, response);
            } else {
                out.println("Error in deposit.");
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

    // Method to fetch current balance from database
    private double getCurrentBalance(Connection conn, String accountNumber) throws SQLException {
        double balance = 0.0;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String query = "SELECT initial_balance FROM customers WHERE account_number = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, accountNumber);
            rs = stmt.executeQuery();
            if (rs.next()) {
                balance = rs.getDouble("initial_balance");
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }
        return balance;
    }

    // Method to insert transaction record into database
    private void insertTransaction(Connection conn, String accountNumber, String transactionType, double amount) throws SQLException {
        PreparedStatement stmt = null;
        try {
            String insertTransaction = "INSERT INTO transactions (account_number, transaction_type, amount) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(insertTransaction);
            stmt.setString(1, accountNumber);
            stmt.setString(2, transactionType);
            stmt.setDouble(3, amount);
            stmt.executeUpdate();
        } finally {
            if (stmt != null) stmt.close();
        }
    }
}
