<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Display User Data</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }
        
        .container {
            background: white;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 10px 40px rgba(0,0,0,0.3);
            width: 100%;
            max-width: 600px;
        }
        
        h1 {
            color: #667eea;
            text-align: center;
            margin-bottom: 30px;
            font-size: 28px;
        }
        
        .data-section {
            background: #f8f9fa;
            padding: 25px;
            border-radius: 10px;
            margin-bottom: 20px;
        }
        
        .data-row {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 15px 0;
            border-bottom: 1px solid #e0e0e0;
        }
        
        .data-row:last-child {
            border-bottom: none;
        }
        
        .data-label {
            font-weight: 600;
            color: #555;
            font-size: 15px;
            min-width: 150px;
        }
        
        .data-value {
            color: #333;
            font-size: 15px;
            text-align: right;
            flex: 1;
            padding: 8px 15px;
            background: white;
            border-radius: 5px;
            margin-left: 15px;
        }
        
        .back-btn {
            display: block;
            width: 100%;
            padding: 14px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
            transition: transform 0.2s ease, box-shadow 0.2s ease;
        }
        
        .back-btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 20px rgba(102, 126, 234, 0.4);
        }
        
        .success-icon {
            text-align: center;
            font-size: 60px;
            color: #4caf50;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <%
        // Receive all form inputs
        String fullName = request.getParameter("fullName");
        String password = request.getParameter("password");
        String age = request.getParameter("age");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
    %>
    
    <div class="container">
        <div class="success-icon">✓</div>
        <h1>Registration Successful!</h1>
        
        <div class="data-section">
            <div class="data-row">
                <span class="data-label">Full Name:</span>
                <span class="data-value"><%= fullName %></span>
            </div>
            
            <div class="data-row">
                <span class="data-label">Password:</span>
                <span class="data-value"><%= password %></span>
            </div>
            
            <div class="data-row">
                <span class="data-label">Age:</span>
                <span class="data-value"><%= age %></span>
            </div>
            
            <div class="data-row">
                <span class="data-label">Address (Radio):</span>
                <span class="data-value"><%= address %></span>
            </div>
            
            <div class="data-row">
                <span class="data-label">City (Select):</span>
                <span class="data-value"><%= city %></span>
            </div>
        </div>
        
        <a href="form.jsp" class="back-btn">Back to Form</a>
    </div>
</body>
</html>
