<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/item/css/show-items-new.css">
    
     <style>
    .input-data input[type="text"] ~ label,
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
    <div class="text">Sign Up</div>

    <% String message = (String) request.getAttribute("message");
       if (message != null) { %>
        <script>alert('<%= message %>');</script>
    <% } %>

   <form action="<%= request.getContextPath() %>/UserController" method="post" onsubmit="return validateSignup()">
    <div class="form-row">
        <div class="input-data">
            <input type="text" name="name" id="name" 
                   autocomplete="new-password"
                   onfocus="this.removeAttribute('readonly')"
                   readonly>
            <div class="underline"></div>
            <label>Name</label>
        </div>
    </div>
    <div class="form-row">
        <div class="input-data">
            <input type="text" name="email" id="email"
                   autocomplete="new-password"
                   onfocus="this.removeAttribute('readonly')"
                   readonly>
            <div class="underline"></div>
            <label>Email</label>
        </div>
    </div>
    <div class="form-row">
        <div class="input-data">
            <input type="password" name="password" id="password"
                   autocomplete="new-password"
                   onfocus="this.removeAttribute('readonly')"
                   readonly>
            <div class="underline"></div>
            <label>Password</label>
        </div>
    </div>
    <input type="hidden" name="action" value="signup">
    <input type="submit" value="Sign Up" class="button">
</form>

    <p class="back">
        <a href="<%= request.getContextPath() %>/item/login.jsp">Already have an account? Login</a>
    </p>
</div>

<script>
function validateSignup() {
    const name = document.getElementById('name').value.trim();
    const email = document.getElementById('email').value.trim();
    const password = document.getElementById('password').value.trim();
    if (name.length < 2) { alert('Name must be at least 2 characters'); return false; }
    if (email === '') { alert('Email is required'); return false; }
    if (password.length < 6) { alert('Password must be at least 6 characters'); return false; }
    return true;
}
</script>
</body>
</html>