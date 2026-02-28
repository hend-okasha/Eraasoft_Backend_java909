<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/item/css/show-items-new.css">
      <style>
    .input-data input[type="email"] ~ label,
    .input-data input[type="password"] ~ label {
        top: 0;
        font-size: 0.85rem;
        color: #3a7bd5;
        font-weight: 600;
    }
</style>
</head>
<body>
<div class="container">
    <div class="text">Login</div>

    <% String message = (String) request.getAttribute("message");
       if (message != null) { %>
        <script>alert('<%= message %>');</script>
    <% } %>

    <form action="<%= request.getContextPath() %>/UserController" method="post" onsubmit="return validateLogin()">
        <div class="form-row">
            <div class="input-data">
                <input type="email" required name="email" id="email" placeholder = " ">
                <div class="underline"></div>
                <label>Email</label>
            </div>
        </div>
        <div class="form-row">
            <div class="input-data">
                <input type="password" required name="password" id="password" placeholder = " ">
                <div class="underline"></div>
                <label>Password</label>
            </div>
        </div>
        <input type="hidden" name="action" value="login">
        <input type="submit" value="Login" class="button">
    </form>

    <p class="back">
        <a href="<%= request.getContextPath() %>/item/signup.jsp">Don't have an account? Sign up</a>
    </p>
</div>

<script>
function validateLogin() {
    const email = document.getElementById('email').value.trim();
    const password = document.getElementById('password').value.trim();
    if (email === '') { alert('Email is required'); return false; }
    if (password === '') { alert('Password is required'); return false; }
    return true;
}
</script>
</body>
</html>