package servlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

@WebServlet("/UpdateCustomerServlet")
public class UpdateCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        int id = Integer.parseInt(request.getParameter("id"));
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String mobileNo = request.getParameter("mobileNo");
        String email = request.getParameter("email");
        String accountType = request.getParameter("accountType");
        String dob = request.getParameter("dob");
        String idProof = request.getParameter("idProof");

        Connection conn = null;
        PreparedStatement pst = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "1234");

            String updateCustomer = "UPDATE customers SET full_name=?, address=?, mobile_no=?, email=?, account_type=?, date_of_birth=?, id_proof=? WHERE id=?";
            pst = conn.prepareStatement(updateCustomer);
            pst.setString(1, fullName);
            pst.setString(2, address);
            pst.setString(3, mobileNo);
            pst.setString(4, email);
            pst.setString(5, accountType);
            pst.setString(6, dob);
            pst.setString(7, idProof);
            pst.setInt(8, id);

            int row = pst.executeUpdate();

            if (row > 0) {
                out.println("Customer details updated successfully.");
                response.sendRedirect("view_customers.jsp");
            } else {
                out.println("Error in updating customer details.");
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

