<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.Cookie" %>
<%
    String favPlace = "You haven't selected a favorite place yet.";

    Cookie[] cookies = request.getCookies();
    if(cookies != null){
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("fav_place")){
                favPlace = cookie.getValue();
                break;
            }
        }
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Homepage</title>
<style>
    body {
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        background: #eef2f3;  
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
        color: #333;
    }
    .container {
        background: #fff; 
        padding: 30px 25px;
        border-radius: 12px;
        text-align: center;
        box-shadow: 0 8px 20px rgba(0,0,0,0.2); 
    }
    h2 {
        margin-bottom: 15px;
        color: #2980b9; 
    }
    p {
        font-size: 20px;
        font-weight: bold;
        color: #555;
    }
</style>
</head>
<body>
<div class="container">
    <h2>Your Favorite Place:</h2>
    <p><%= favPlace %></p>
</div>
</body>
</html>
