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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role"); // Assuming role is selected in login.jsp

        boolean isAuthenticated = false;
        String dashboardPage = null;

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "1234");

            if ("admin".equals(role)) {
                // Check if the user is an admin
                String adminQuery = "SELECT * FROM users WHERE username=? AND password=?";
                pst = conn.prepareStatement(adminQuery);
                pst.setString(1, username);
                pst.setString(2, password);
                rs = pst.executeQuery();

                if (rs.next()) {
                    isAuthenticated = true;
                    dashboardPage = "admin_dashboard.jsp";
                }
            } else if ("customer".equals(role)) {
                // Check if the user is a customer
                String customerQuery = "SELECT * FROM users WHERE username=? AND password=?";
                pst = conn.prepareStatement(customerQuery);
                pst.setString(1, username);
                pst.setString(2, password);
                rs = pst.executeQuery();

                if (rs.next()) {
                    isAuthenticated = true;
                    dashboardPage = "customer_dashboard.jsp";
                }
            }

            if (isAuthenticated) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("role", role);
                response.sendRedirect(dashboardPage); // Redirect to appropriate dashboard
            } else {
                out.println("Invalid username or password. Please try again.");
                request.getRequestDispatcher("login.jsp").include(request, response);
            }
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pst != null)
                    pst.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
                out.println("Error in closing database resources: " + ex.getMessage());
            }
            out.close();
        }
    }
}
