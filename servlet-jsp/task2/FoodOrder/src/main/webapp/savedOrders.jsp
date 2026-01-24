<%@page import="java.util.Objects"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Orders</title>
<style>
    * {
        box-sizing: border-box;
        margin: 0;
        padding: 0;
        font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
    }

    body {
        min-height: 100vh;
        background: linear-gradient(135deg, #667eea, #764ba2);
        display: flex;
        justify-content: center;
        align-items: center;
        padding: 20px;
    }

    .result-box {
        background: white;
        padding: 30px 35px;
        border-radius: 15px;
        box-shadow: 0 10px 35px rgba(0,0,0,0.3);
        width: 100%;
        max-width: 500px;
    }

    h2 {
        text-align: center;
        color: #667eea;
        margin-bottom: 20px;
        font-size: 24px;
    }

    .order-value {
        background: #f8f9fa;
        padding: 12px 15px;
        border-radius: 8px;
        margin-bottom: 12px;
        font-size: 16px;
        color: #333;
        box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        transition: transform 0.2s ease;
    }

    .order-value:hover {
        transform: translateX(5px);
        background: #e0e7ff;
    }

    .back-btn {
        display: block;
        width: 100%;
        text-align: center;
        padding: 14px;
        background: linear-gradient(135deg, #667eea, #764ba2);
        color: white;
        border-radius: 8px;
        text-decoration: none;
        font-weight: bold;
        font-size: 16px;
        margin-top: 20px;
        transition: transform 0.2s ease, box-shadow 0.2s ease;
    }

    .back-btn:hover {
        transform: translateY(-2px);
        box-shadow: 0 6px 20px rgba(102, 126, 234, 0.4);
    }

    .no-orders {
        text-align: center;
        color: #666;
        font-style: italic;
        padding: 20px;
    }
</style>
</head>
<body>

<%
    
    List<String> savedOrders = (List<String>) session.getAttribute("allOrders");
  
    if (savedOrders == null) {
        savedOrders = new ArrayList<>();
        session.setAttribute("allOrders", savedOrders);
    }
    
    String selectedOrder = request.getParameter("order");
  
    if (selectedOrder != null && !selectedOrder.trim().isEmpty()) {
        savedOrders.add(selectedOrder);
        
        session.setAttribute("allOrders", savedOrders);
    }
%>

<div class="result-box">
    <h2>Selected Orders</h2>
    
    <%
        if (!savedOrders.isEmpty()) {
            for (String order : savedOrders) {
    %>
                <div class='order-value'><%= order %></div>
    <%
            }
        } else {
    %>
            <div class='no-orders'>No orders selected yet</div>
    <%
        }
    %>

    <a href="order.html" class="back-btn">Back to Order Form</a>
</div>

</body>
</html>