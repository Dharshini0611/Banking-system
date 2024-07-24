<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Customers</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f0f0f0; }
        .container { max-width: 800px; margin: auto; background: #fff; padding: 15px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); margin-top: 30px; }
        table { width: 100%; border-collapse: collapse; font-size: 12px; }
        table, th, td { border: 1px solid black; }
        th, td { padding: 3px; text-align: center; }
        th { background-color: #f2f2f2; }
        a { text-decoration: none; padding: 3px 8px; background-color: #4CAF50; color: white; border-radius: 4px; font-size: 10px; }
        a:hover { background-color: #45a049; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Registered Customers</h2>
        <table>
            <tr>
                <th>Full Name</th>
                <th>Address</th>
                <th>Mobile No</th>
                <th>Email</th>
                <th>Account Type</th>
                <th>Initial Balance</th>
                <th>Date of Birth</th>
                <th>ID Proof</th>
                <th>Account Number</th>
                <th>Actions</th>
            </tr>
            <%
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "1234");

                    stmt = conn.createStatement();
                    String query = "SELECT * FROM customers";
                    rs = stmt.executeQuery(query);

                    while (rs.next()) {
                        %>
                        <tr>
                            <td><%= rs.getString("full_name") %></td>
                            <td><%= rs.getString("address") %></td>
                            <td><%= rs.getString("mobile_no") %></td>
                            <td><%= rs.getString("email") %></td>
                            <td><%= rs.getString("account_type") %></td>
                            <td><%= rs.getDouble("initial_balance") %></td>
                            <td><%= rs.getDate("date_of_birth") %></td>
                            <td><%= rs.getString("id_proof") %></td>
                            <td><%= rs.getString("account_number") %></td>
                            <td>
                                <a href="edit_customer.jsp?id=<%= rs.getInt("id") %>">Edit</a>
                                <a href="DeleteCustomerServlet?id=<%= rs.getInt("id") %>">Delete</a>
                            </td>
                        </tr>
                        <%
                    }
                } catch (Exception e) {
                    out.println("Error: " + e.getMessage());
                } finally {
                    try {
                        if (rs != null) rs.close();
                        if (stmt != null) stmt.close();
                        if (conn != null) conn.close();
                    } catch (SQLException ex) {
                        out.println("Error in closing database resources: " + ex.getMessage());
                    }
                }
            %>
        </table>
    </div>
</body>
</html>
