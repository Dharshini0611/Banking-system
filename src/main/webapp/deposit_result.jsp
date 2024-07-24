<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Deposit Result</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
        }
        .container {
            max-width: 400px;
            margin: auto;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            margin-top: 100px;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        .message {
            margin-top: 20px;
            text-align: center;
        }
        .success {
            color: green;
        }
        .btn-container {
            margin-top: 20px;
            text-align: center;
        }
        .btn-container a {
            display: inline-block;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }
        .btn-container a:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Deposit Successful</h2>
        <div class="message">
            <p class="success">Deposit successful. Current Balance: $<%= request.getAttribute("currentBalance") %></p>
        </div>
        <div class="btn-container">
            <a href="customer_dashboard.jsp">Back to Dashboard</a>
        </div>
    </div>
</body>
</html>
