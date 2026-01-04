<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.Cookie" %>
<%
    String favPlace = request.getParameter("favPlace");
    
    if(favPlace != null && !favPlace.isEmpty()) {
        Cookie cookie = new Cookie("fav_place", favPlace);
        cookie.setMaxAge(30*24*60*60); 
        response.addCookie(cookie);
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Saved Favorite Place</title>
<style>
    body {
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        background: #f1f2f6;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
    }
    .container {
        background: white;
        padding: 25px 30px;
        border-radius: 12px;
        box-shadow: 0 6px 15px rgba(0,0,0,0.2);
        text-align: center;
    }
    a {
        text-decoration: none;
        color: white;
        background-color: #2980b9;
        padding: 10px 20px;
        border-radius: 6px;
        transition: 0.3s;
    }
    a:hover {
        background-color: #1f618d;
    }
</style>
</head>
<body>
<div class="container">
    <h2>Your favorite place has been saved!</h2>
    <a href="homePage.jsp">Go to Homepage</a>
</div>
</body>
</html>
