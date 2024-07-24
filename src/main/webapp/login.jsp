<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            max-width: 400px;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }
        input, select {
            width: calc(100% - 16px);
            padding: 8px;
            margin: 0;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
            box-sizing: border-box;
        }
        button {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            margin-top: 20px;
        }
        button:hover {
            background-color: #45a049;
        }
        .checkbox-group {
            display: flex;
            align-items: center;
            margin-top: 15px;
        }
        .checkbox-group input {
            width: auto;
            margin-right: 10px;
        }
    </style>
    <script>
        function checkCustomerExists() {
            var checkbox = document.getElementById("customerExists");
            if (checkbox.checked) {
                window.location.href = "customer_login.jsp";
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
    <div class="container">
        <h2>Login</h2>
        <%
            String message = (String) request.getAttribute("message");
            String accountNumber = (String) request.getAttribute("accountNumber");
            String tempPassword = (String) request.getAttribute("tempPassword");
            if (message != null) {
        %>
            <p style="color: green; text-align: center;"><%= message %></p>
        <% } %>
        <form action="LoginServlet" method="post" onsubmit="return checkCustomerExists()">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" value="<%= accountNumber != null ? accountNumber : "" %>" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" value="<%= tempPassword != null ? tempPassword : "" %>" required>
            </div>
            <div class="form-group">
                <label for="role">Role:</label>
                <select id="role" name="role" required>
                    <option value="admin">Admin</option>
                    <option value="customer">Customer</option>
                </select>
            </div>
            <div class="checkbox-group">
                <input type="checkbox" id="customerExists" name="customerExists">
                <label for="customerExists">If the customer already exists, click here to redirect to the customer login page.</label>
            </div>
            <button type="submit">Login</button>
        </form>
    </div>
</body>
</html>
