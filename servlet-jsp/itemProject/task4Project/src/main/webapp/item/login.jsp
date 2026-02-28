<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login — Item Service</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/item/css/show-items.css">
</head>
<body>

<div class="auth-container">
    <div class="auth-card">

        <!-- Logo -->
        <div class="auth-logo">
            <div class="logo-icon">📦</div>
            <h2>Item Service</h2>
            <p>Sign in to your account</p>
        </div>

        <!-- Error message -->
        <% String message = (String) request.getAttribute("message");
           if (message != null) { %>
        <div class="toast toast-error" style="position:relative;top:auto;right:auto;margin:20px 40px 0;animation:none;">
            <span class="toast-icon">❌</span>
            <span><%= message %></span>
        </div>
        <% } %>

        <!-- Form -->
        <div class="auth-body">
            <form action="<%= request.getContextPath() %>/UserController" method="post"
                  onsubmit="return validateLogin()" autocomplete="off">

                <div class="input-data">
                    <label for="email">Email Address</label>
                    <input type="text" name="email" id="email"
                           placeholder="you@example.com"
                           autocomplete="new-password"
                           onfocus="this.removeAttribute('readonly')" readonly>
                </div>

                <div class="input-data">
                    <label for="password">Password</label>
                    <input type="password" name="password" id="password"
                           placeholder="••••••••"
                           autocomplete="new-password"
                           onfocus="this.removeAttribute('readonly')" readonly>
                </div>

                <input type="hidden" name="action" value="login">
                <input type="submit" value="Sign In" class="button" style="margin:20px 0 0;width:100%;">
            </form>
        </div>

        <div class="divider" style="margin:0 40px;">or</div>

        <div class="auth-footer">
            <p style="color:#64748b;font-size:0.9rem;margin-bottom:8px;">Don't have an account?</p>
            <a href="<%= request.getContextPath() %>/item/signup.jsp">Create an account →</a>
        </div>

    </div>
</div>

<script>
function validateLogin() {
    const email = document.getElementById('email').value.trim();
    const password = document.getElementById('password').value.trim();
    if (!email) { alert('Email is required'); return false; }
    if (!password) { alert('Password is required'); return false; }
    return true;
}
</script>
</body>
</html>
