<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 600px;
            margin: auto;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-top: 100px;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        ul li {
            margin: 10px 0;
        }
        ul li a {
            text-decoration: none;
            color: #fff;
            font-weight: bold;
            display: block;
            padding: 10px;
            border: 1px solid #4CAF50;
            border-radius: 4px;
            background-color: #4CAF50;
            text-align: center;
        }
        ul li a:hover {
            background-color: #45a049;
        }
        .logout {
            display: block;
            text-align: center;
            margin-top: 20px;
            text-decoration: none;
            color: #fff;
            font-weight: bold;
            padding: 10px;
            border: 1px solid #4CAF50;
            border-radius: 4px;
            background-color: #4CAF50;
        }
        .logout:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Welcome, <%= request.getSession().getAttribute("username") %></h2>
        <ul>
            <li><a href="ViewCustomerDetailServlet">View Customer Account Detail</a></li>
            <li><a href="ViewBalanceServlet">Balance Account Detail</a></li>
            <li><a href="deposit.jsp">Deposit</a></li>
            <li><a href="withdraw.jsp">Withdraw</a></li>
            <li><a href="ViewTransactionsServlet">Transaction History</a></li>
        </ul>
        <a class="logout" href="logout.jsp">Logout</a>
    </div>
</body>
</html>
