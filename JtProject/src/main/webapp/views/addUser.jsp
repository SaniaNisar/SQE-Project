<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add User</title>
    <!-- Add your CSS or Bootstrap links here if needed -->
</head>
<body>
    <div>
        <h2>Add User</h2>
      <form action="${pageContext.request.contextPath}/admin/addUser" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required><br>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required><br>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required><br>

            <label for="role">Role:</label>
            <input type="text" id="role" name="role" required><br>

            <label for="address">Address:</label>
            <input type="text" id="address" name="address" required><br>

            <button type="submit">Add User</button>
        </form>
        <br>
        <a href="${pageContext.request.contextPath}/admin/customers">Back to Customer List</a>
    </div>
    <!-- Add your scripts or additional HTML if needed -->
</body>
</html>
