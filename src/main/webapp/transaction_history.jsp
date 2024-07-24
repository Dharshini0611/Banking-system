<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Transaction History</title>
</head>
<body>
    <h2>Transaction History</h2>
    <button onclick="window.print()">print</button>
    <table border="1">
        <tr>
            <th>Date</th>
            <th>Transaction Type</th>
            <th>Amount</th>
            <th>Balance</th>
        </tr>
        <%
            String accountNumber = (String) session.getAttribute("accountNumber");

            Connection conn = null;
            PreparedStatement pst = null;
            ResultSet rs = null;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "1234");

                String query = "SELECT * FROM transactions WHERE account_number = ? ORDER BY transaction_date DESC LIMIT 10";
                pst = conn.prepareStatement(query);
                pst.setString(1, accountNumber);
                rs = pst.executeQuery();

                while (rs.next()) {
                    %>
                    <tr>
                        <td><%= rs.getTimestamp("transaction_date") %></td>
                        <td><%= rs.getString("transaction_type") %></td>
                        <td><%= rs.getDouble("amount") %></td>
                        <td><%= rs.getDouble("balance") %></td>
                    </tr>
                    <%
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
    </table>
</body>
</html>
