package servlet;

import java.io.IOException;
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

@WebServlet("/CustomerLogin")
public class CustomerLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection details
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/db"; // Replace 'db' with your actual database name
    private static final String jdbcUser = "root"; // Replace with your MySQL username
    private static final String jdbcPassword = "1234"; // Replace with your MySQL password

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Debug: Print received parameters
        System.out.println("Received Username: " + username);
        System.out.println("Received Password: " + password);

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish MySQL Connection
            conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);

            // Prepare SQL query to check credentials
            String sql = "SELECT * FROM usercredentials WHERE username = ? AND password = ?";
            System.out.println("Executing Query: " + sql);

            statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            // Execute the query
            resultSet = statement.executeQuery();

            // Check if the user exists
            if (resultSet.next()) {
                // User authenticated
                // Set session attribute with username
                HttpSession session = request.getSession();
                session.setAttribute("username", username);

                // Redirect to the customer dashboard
                response.sendRedirect("customer_dashboard.jsp"); // Adjust the redirection URL as per your project structure
            } else {
                // Redirect back to login page with error message if credentials are invalid
                response.sendRedirect("login.jsp?error=InvalidCredentials");
            }
        } catch (Exception e) {
            // Print stack trace and return error message in case of exception
            e.printStackTrace();
            response.getWriter().println("Exception: " + e.getMessage());
        } finally {
            // Close resources in finally block
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                response.getWriter().println("Error in closing database resources: " + ex.getMessage());
            }
        }
    }
}
