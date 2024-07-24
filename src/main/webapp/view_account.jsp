<!DOCTYPE html>
<html>
<head>
    <title>View Customer Details</title>
</head>
<body>
    <h2>Enter Customer Details</h2>
    <form action="ViewCustomersServlet" method="get">
        <label for="username">Account Number:</label>
        <input type="text" id="username" name="username" required>
        <br><br>
        <label for="idProof">ID Proof:</label>
        <input type="text" id="idProof" name="idProof" required>
        <br><br>
        <input type="submit" value="submit">
    </form>
</body>
</html>
