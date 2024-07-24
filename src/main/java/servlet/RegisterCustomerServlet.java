package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterCustomerServlet")
public class RegisterCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String mobileNo = request.getParameter("mobileNo");
        String email = request.getParameter("email");
        String accountType = request.getParameter("accountType");
        double initialBalance = Double.parseDouble(request.getParameter("initialBalance"));
        String dob = request.getParameter("dob");
        String idProof = request.getParameter("idProof");

        // Generate account number and temporary password
        String accountNumber = generateAccountNumber();
        String tempPassword = generateTemporaryPassword();

        Connection conn = null;
        PreparedStatement pst = null;
        PreparedStatement pstCredential = null;

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish MySQL Connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "1234");

            // Prepare SQL statement for inserting customer details
            String insertCustomer = "INSERT INTO customers (full_name, address, mobile_no, email, account_type, initial_balance, date_of_birth, id_proof, account_number, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pst = conn.prepareStatement(insertCustomer);
            pst.setString(1, fullName);
            pst.setString(2, address);
            pst.setString(3, mobileNo);
            pst.setString(4, email);
            pst.setString(5, accountType);
            pst.setDouble(6, initialBalance);
            pst.setString(7, dob);
            pst.setString(8, idProof);
            pst.setString(9, accountNumber);
            pst.setString(10, tempPassword);

            // Execute the insert statement
            int rowsInserted = pst.executeUpdate();

            if (rowsInserted > 0) {
                // Insert the account number and temporary password into the usercredential table
                String insertCredential = "INSERT INTO usercredentials (username, password) VALUES (?, ?)";
                pstCredential = conn.prepareStatement(insertCredential);
                pstCredential.setString(1, accountNumber);
                pstCredential.setString(2, tempPassword);
                pstCredential.executeUpdate();

                // Success message
                request.setAttribute("message", "Customer registered successfully with Account Number: " + accountNumber + " and Temporary Password: " + tempPassword);
                request.getRequestDispatcher("registration_success.jsp").forward(request, response);
            } else {
                // Error message if no rows were inserted
                request.setAttribute("message", "Error in customer registration. No rows inserted.");
                request.getRequestDispatcher("registration_success.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException | SQLException e) {
            // Handle exceptions
            request.setAttribute("message", "Error: " + e.getMessage());
            request.getRequestDispatcher("registration_success.jsp").forward(request, response);
        } finally {
            // Close resources in finally block
            try {
                if (pst != null) {
                    pst.close();
                }
                if (pstCredential != null) {
                    pstCredential.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                request.setAttribute("message", "Error in closing database resources: " + ex.getMessage());
                request.getRequestDispatcher("registration_success.jsp").forward(request, response);
            }
        }
    }

    // Method to generate a unique account number
    private String generateAccountNumber() {
        // Implement your logic to generate account number
        // Example implementation:
        return "ACC" + System.currentTimeMillis();
    }

    // Method to generate a temporary password
    private String generateTemporaryPassword() {
        // Implement your logic to generate temporary password
        // Example implementation:
        return "temp" + System.currentTimeMillis();
    }
}
