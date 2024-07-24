<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Logout</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f0f0f0; }
        .container { max-width: 400px; margin: auto; background: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); margin-top: 100px; }
        h2 { text-align: center; color: #333; }
        p { text-align: center; }
        button { width: 100%; padding: 10px; background-color: #4CAF50; color: #fff; border: none; border-radius: 4px; cursor: pointer; font-size: 16px; }
        button:hover { background-color: #45a049; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Logout Successful</h2>
        <p>You have been successfully logged out.</p>
        <button onclick="redirectToLogin()">Login Again</button>
    </div>

    <script>
        function redirectToLogin() {
            window.location.href = "login.jsp";
        }
    </script>
</body>
</html>
