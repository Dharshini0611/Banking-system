<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h2 {
            text-align: center;
            color: #333;
        }
        nav ul {
            list-style-type: none;
            padding: 0;
            text-align: center;
        }
        nav ul li {
            display: block;
            margin-bottom: 10px;
        }
        nav ul li a {
            display: block;
            text-decoration: none;
            color: #fff; /* Text color */
            font-weight: bold;
            padding: 15px 30px;
            border: 1px solid #ccc;
            border-radius: 4px;
            background-color: #4CAF50; /* Green background color */
            transition: all 0.3s ease;
            text-align: center;
        }
        nav ul li a:hover {
            background-color: #45a049; /* Darker green on hover */
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Welcome, Admin</h2>
        <nav>
            <ul>
                <li><a href="register_customer.jsp">Register Customer</a></li>
                <li><a href="view_customers.jsp">View Customers</a></li>
            </ul>
        </nav>
    </div>
</body>
</html>


    