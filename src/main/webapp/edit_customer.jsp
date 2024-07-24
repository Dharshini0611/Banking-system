<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Customer</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f0f0f0; }
        .container { max-width: 500px; margin: auto; background: #fff; padding: 15px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); margin-top: 50px; }
        h2 { text-align: center; color: #333; font-size: 18px; }
        form { display: flex; flex-direction: column; }
        label { margin-bottom: 5px; font-weight: bold; color: #555; font-size: 12px; }
        input, select { margin-bottom: 10px; padding: 5px; border: 1px solid #ccc; border-radius: 4px; font-size: 12px; }
        button { padding: 8px; background-color: #4CAF50; color: white; border: none; border-radius: 4px; cursor: pointer; font-size: 14px; }
        button:hover { background-color: #45a049; }
        .form-group { margin-bottom: 10px; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Edit Customer</h2>
        <%
            int id = Integer.parseInt(request.getParameter("id"));

            Connection conn = null;
            PreparedStatement pst = null;
            ResultSet rs = null;

            String fullName = "", address = "", mobileNo = "", email = "", accountType = "", dob = "", idProof = "";

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "1234");

                String query = "SELECT * FROM customers WHERE id=?";
                pst = conn.prepareStatement(query);
                pst.setInt(1, id);
                rs = pst.executeQuery();

                if (rs.next()) {
                    fullName = rs.getString("full_name");
                    address = rs.getString("address");
                    mobileNo = rs.getString("mobile_no");
                    email = rs.getString("email");
                    accountType = rs.getString("account_type");
                    dob = rs.getString("date_of_birth");
                    idProof = rs.getString("id_proof");
                }
            } catch (Exception e) {
                out.println("Error: " + e.getMessage());
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (pst != null) pst.close();
                    if (conn != null) conn.close();
                } catch (SQLException ex) {
                    out.println("Error in closing database resources: " + ex.getMessage());
                }
            }
        %>
        <form action="UpdateCustomerServlet" method="post">
            <input type="hidden" name="id" value="<%= id %>">

            <div class="form-group">
                <label for="fullName">Full Name:</label>
                <input type="text" id="fullName" name="fullName" value="<%= fullName %>" required>
            </div>

            <div class="form-group">
                <label for="address">Address:</label>
                <input type="text" id="address" name="address" value="<%= address %>" required>
            </div>

            <div class="form-group">
                <label for="mobileNo">Mobile No:</label>
                <input type="text" id="mobileNo" name="mobileNo" value="<%= mobileNo %>" required>
            </div>

            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="<%= email %>" required>
            </div>

            <div class="form-group">
                <label for="accountType">Account Type:</label>
                <select id="accountType" name="accountType" required>
                    <option value="Saving" <%= accountType.equals("Saving") ? "selected" : "" %>>Saving</option>
                    <option value="Current" <%= accountType.equals("Current") ? "selected" : "" %>>Current</option>
                </select>
            </div>

            <div class="form-group">
                <label for="dob">Date of Birth:</label>
                <input type="date" id="dob" name="dob" value="<%= dob %>" required>
            </div>

            <div class="form-group">
                <label for="idProof">ID Proof:</label>
                <input type="text" id="idProof" name="idProof" value="<%= idProof %>" required>
            </div>

            <button type="submit">Update</button>
        </form>
    </div>
</body>
</html>
