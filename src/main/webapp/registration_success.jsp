<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Success</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f0f0f0; }
        .container { max-width: 600px; margin: auto; background: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); margin-top: 100px; text-align: center; }
        h2 { color: #333; }
        p { font-size: 18px; color: #555; }
        a { display: inline-block; margin-top: 20px; padding: 10px 20px; background-color: #4CAF50; color: #fff; text-decoration: none; border-radius: 4px; }
        a:hover { background-color: #45a049; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Registration Status</h2>
        <p><%= request.getAttribute("message") %></p>
        <a href="customer_login.jsp">Go to Login Page</a>
    </div>
</body>
</html>
