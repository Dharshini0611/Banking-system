<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Deposit</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f0f0f0; }
        .container { max-width: 400px; margin: auto; background: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); margin-top: 100px; }
        h2 { text-align: center; color: #333; }
        .form-group { margin-bottom: 15px; }
        label { display: block; font-weight: bold; color: #333; }
        input[type="number"] { width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 4px; font-size: 16px; }
        button { width: 100%; padding: 10px; background-color: #4CAF50; color: #fff; border: none; border-radius: 4px; cursor: pointer; font-size: 16px; }
        button:hover { background-color: #45a049; }
        .message { margin-top: 20px; text-align: center; }
        .error { color: red; }
        .success { color: green; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Deposit Money</h2>
        <form action="DepositServlet" method="post">
            <div class="form-group">
                <label for="amount">Amount:</label>
                <input type="number" id="amount" name="amount" min="1" required>
            </div>
            <button type="submit">Deposit</button>
        </form>
        
        <!-- Display messages -->
        <div class="message">
            <% String message = (String) request.getAttribute("message");
               if (message != null) {
                   String cssClass = (Boolean.parseBoolean(request.getAttribute("isError").toString())) ? "error" : "success";
            %>
                   <p class="<%= cssClass %>"><%= message %></p>
            <% } %>
        </div>
    </div>
</body>
</html>
