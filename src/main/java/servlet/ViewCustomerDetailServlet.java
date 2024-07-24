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

@WebServlet("/ViewCustomerDetailServlet")
public class ViewCustomerDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("username");

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "1234");
            String sql = "SELECT full_name, address, mobile_no, email, account_type, date_of_birth, id_proof FROM customers WHERE account_number = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Store customer details in request attributes
                request.setAttribute("fullName", resultSet.getString("full_name"));
                request.setAttribute("address", resultSet.getString("address"));
                request.setAttribute("mobileNo", resultSet.getString("mobile_no"));
                request.setAttribute("email", resultSet.getString("email"));
                request.setAttribute("accountType", resultSet.getString("account_type"));
                request.setAttribute("dob", resultSet.getDate("date_of_birth"));
                request.setAttribute("idProof", resultSet.getString("id_proof"));
                request.getRequestDispatcher("view_customer_detail.jsp").forward(request, response);
            } else {
                response.sendRedirect("customer_dashboard.jsp?error=DetailsNotFound");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Exception: " + e.getMessage());
        } finally {
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
