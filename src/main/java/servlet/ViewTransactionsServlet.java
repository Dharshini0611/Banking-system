package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ViewTransactionsServlet")
public class ViewTransactionsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String accountNumber = (String) session.getAttribute("username");

        if (accountNumber == null) {
            response.getWriter().println("You must be logged in to view transactions.");
            return;
        }

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish Connection to MySQL database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "1234");

            // Fetch transactions for the logged-in customer
            String fetchTransactionsQuery = "SELECT * FROM transactions WHERE account_number = ?";
            stmt = conn.prepareStatement(fetchTransactionsQuery);
            stmt.setString(1, accountNumber);

            rs = stmt.executeQuery();

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><head><title>Transaction History</title>");
            out.println("<style>");
            out.println("body {font-family: Arial, sans-serif; background-color: #f0f0f0;}");
            out.println(".container {max-width: 1000px; margin: auto; background: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); margin-top: 30px;}");
            out.println("table {width: 100%; border-collapse: collapse; margin: 20px 0;}");
            out.println("table, th, td {border: 1px solid #ddd;}");
            out.println("th, td {padding: 12px; text-align: left;}");
            out.println("th {background-color: #f4f4f4;}");
            out.println(".center {text-align: center;}");
            out.println(".btn {text-decoration: none; padding: 10px 20px; background-color: #4CAF50; color: white; border-radius: 5px;}");
            out.println(".btn:hover {background-color: #45a049;}");
            out.println("</style></head><body>");
            out.println("<div class='container'>");
            out.println("<h2 class='center'>Transaction History</h2>");
            out.println("<button onclick=\"window.print()\">print</button>");

            out.println("<table>");
            out.println("<tr><th>Transaction Date</th><th>Type</th><th>Amount</th></tr>");

            while (rs.next()) {
                out.println("<tr><td>" + rs.getDate("transaction_date") + "</td>");
                out.println("<td>" + rs.getString("transaction_type") + "</td>");
                out.println("<td>" + rs.getDouble("amount") + "</td></tr>");
            }

            out.println("</table>");
            out.println("<div class='center' style='margin-top: 20px;'>");
            out.println("<a href='customer_dashboard.jsp' class='btn'>Go to Dashboard</a>");
            out.println("</div>");
            out.println("</div>");
            out.println("</body></html>");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Exception: " + e.getMessage());
        } finally {
            // Close JDBC objects in finally block
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.getWriter().println("Exception in closing database resources: " + e.getMessage());
            }
        }
    }
}
