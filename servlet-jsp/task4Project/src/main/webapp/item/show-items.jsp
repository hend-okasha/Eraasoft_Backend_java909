<%@page import="item.model.Item"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Show Items</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/item/css/show-items.css">

</head>
<body>
<div class="layer">
    <table>
        <h1>Items</h1>
        <thead>
        <tr>
            <th>ID</th>
            <th>NAME</th>
            <th>PRICE</th>
            <th>TOTAL_NUMBER</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <%
        	List<Item> items = (List<Item>)request.getAttribute("allItems");
        	
        	for(Item item : items){
        %>
        <tr>
            <td><%= item.getId()%></td>
            <td><%= item.getName()%></td>
            <td><%= item.getPrice()%></td>
            <td><%= item.getTotalNumber()%></td>
            <td>
                <a>Update</a>
                <a href="/ItemService/ItemController?action=remove-item&id=<%=item.getId()%>">Delete</a>
            </td>
        </tr>
       <% } %>
        </tbody>
    </table>


    <button class="f"><a href="./item/add-item.html" >Add Item</a></button>


</div>

</body>
</html>