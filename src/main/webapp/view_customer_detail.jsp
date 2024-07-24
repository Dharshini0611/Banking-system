<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Details</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f0f0f0; }
        .container { max-width: 600px; margin: auto; background: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); margin-top: 50px; }
        h2 { text-align: center; color: #333; }
        .details { margin-bottom: 15px; }
        .details label { font-weight: bold; }
        .details span { display: inline-block; width: 80%; }
        .btn-container { text-align: center; margin-top: 20px; }
        .btn-container a { text-decoration: none; padding: 10px 20px; background-color: #4CAF50; color: #fff; border-radius: 4px; }
        .btn-container a:hover { background-color: #45a049; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Customer Details</h2>
        <div class="details">
            <label>Full Name:</label>
            <span><%= request.getAttribute("fullName") %></span>
        </div>
        <div class="details">
            <label>Address:</label>
            <span><%= request.getAttribute("address") %></span>
        </div>
        <div class="details">
            <label>Mobile No:</label>
            <span><%= request.getAttribute("mobileNo") %></span>
        </div>
        <div class="details">
            <label>Email:</label>
            <span><%= request.getAttribute("email") %></span>
        </div>
        <div class="details">
            <label>Account Type:</label>
            <span><%= request.getAttribute("accountType") %></span>
        </div>
        <div class="details">
            <label>Date of Birth:</label>
            <span><%= request.getAttribute("dob") %></span>
        </div>
        <div class="details">
            <label>ID Proof:</label>
            <span><%= request.getAttribute("idProof") %></span>
        </div>
        <div class="btn-container">
            <a href="customer_dashboard.jsp">Back to Dashboard</a>
        </div>
    </div>
</body>
</html>
