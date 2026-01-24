<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Data</title>

<style>
    * {
        box-sizing: border-box;
        margin: 0;
        padding: 0;
        font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
    }

    body {
        min-height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
        background: linear-gradient(135deg, #667eea, #764ba2);
    }

    .container {
        background: white;
        padding: 40px 45px;
        border-radius: 15px;
        box-shadow: 0 12px 35px rgba(0, 0, 0, 0.3);
        width: 100%;
        max-width: 400px;
        text-align: center;
    }

    h1 {
        margin-bottom: 20px;
        font-size: 22px;
        color: #333;
    }

    h1 span {
        color: #667eea;
        font-weight: bold;
    }

    .back-btn {
        display: inline-block;
        margin-top: 25px;
        padding: 12px 25px;
        background: #667eea;
        color: white;
        text-decoration: none;
        border-radius: 8px;
        font-weight: bold;
        transition: background 0.3s, transform 0.2s;
    }

    .back-btn:hover {
        background: #5a6fe3;
        transform: translateY(-2px);
    }
</style>

</head>
<body>

<%
    String name = request.getParameter("name");
    String age = request.getParameter("age");
%>

<div class="container">
    <h1>Name: <span><%= name %></span></h1>
    <h1>Age: <span><%= age %></span></h1>

    <a href="student.html" class="back-btn">Back</a>
</div>

</body>
</html>
