<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>JSP Variables and Function</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            margin: 0;
            padding: 50px;
            min-height: 100vh;
        }
        .container {
            background-color: white;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.3);
            max-width: 600px;
            margin: 0 auto;
        }
        h1 {
            color: #667eea;
            text-align: center;
            margin-bottom: 30px;
        }
        .variables {
            background-color: #f8f9fa;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 20px;
        }
        .variables p {
            margin: 10px 0;
            font-size: 16px;
        }
        .result-box {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 25px;
            border-radius: 10px;
            text-align: center;
            font-size: 20px;
            font-weight: bold;
            margin-top: 20px;
            box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
        }
    </style>
</head>
<body>
    <%!
        int id = 12345;
        String name = "Hend Okasha";
        
        
        public String concatenateIdAndName(int empId, String empName) {
            return "ID: " + empId + " - Name: " + empName;
        }
    %>
    
    <%
    
        String result = concatenateIdAndName(id, name);
    %>
    
    <div class="container">
        <h1>JSP Function Task1</h1>
        
        <div class="variables">
            <h3>Defined Variables:</h3>
            <p><strong>ID:</strong> <%= id %></p>
            <p><strong>Name:</strong> <%= name %></p>
        </div>
        
        <h3>Function Call Result:</h3>
        <div class="result-box">
            <%= result %>
        </div>
    </div>
</body>
</html>
