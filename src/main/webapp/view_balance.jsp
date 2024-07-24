<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Current Balance</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f0f0f0; }
        .container { max-width: 400px; margin: auto; background: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); margin-top: 100px; }
        h2 { text-align: center; color: #333; }
        .balance-info { margin-top: 20px; }
        .label { font-weight: bold; color: #333; } /* Default label color */
        .value { margin-left: 10px; color: green; font-weight: bold; } /* Green color for value */
    </style>
</head>
<body>
    <div class="container">
        <h2>Current Balance</h2>
        <div class="balance-info">
            <p><span class="label">Account Number:</span> <span class="value">${accountNumber}</span></p>
            <p><span class="label">Balance:</span> <span class="value">$${balance}</span></p>
        </div>
    </div>
</body>
</html>
