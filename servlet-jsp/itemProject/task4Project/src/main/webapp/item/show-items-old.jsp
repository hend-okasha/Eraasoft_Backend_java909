<%@page import="item.model.ItemDetails"%>
<%@page import="item.model.User"%>
<%@page import="item.model.Item"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    if (session.getAttribute("loggedUser") == null) {
        response.sendRedirect(request.getContextPath() + "/item/login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Show Items</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/item/css/show-items-new.css">

</head>
<body>

<%
    String message = (String) session.getAttribute("message");
    if (message != null) {
%>
    <script>
        alert("<%= message %>");
    </script>
<%
        session.removeAttribute("message");
    }
%>

<div class="layer">

<%
    User loggedUser = (User) session.getAttribute("loggedUser");
    if (loggedUser != null) {
%>
    <div style="text-align:right; margin-bottom:15px;">
        <span>Welcome, <%= loggedUser.getName() %></span>
        &nbsp;&nbsp;
        <a href="<%= request.getContextPath() %>/UserController?action=logout"
           style="padding:8px 20px; background:#dc3545; color:white; border-radius:8px; text-decoration:none;">
            Logout
        </a>
    </div>
<% } %>

    <table>
        <h1>Items</h1>
        <thead>
        <tr>
            <th>ID</th>
            <th>NAME</th>
            <th>PRICE</th>
            <th>TOTAL_NUMBER</th>
            <th>DESCRIPTION</th>
            <th>ISSUE DATE</th>
            <th>EXPIRY DATE</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
         <%
            List<Item> items = (List<Item>) request.getAttribute("allItems");
            for (Item item : items) {
                ItemDetails details = (ItemDetails) null;
                List<ItemDetails> allDetails = (List<ItemDetails>) request.getAttribute("allDetails");
                if (allDetails != null) {
                    for (ItemDetails d : allDetails) {
                        if (d.getItemId().equals(item.getId())) {
                            details = d;
                            break;
                        }
                    }
                }
        %>
        <tr>
            <td><%= item.getId() %></td>
            <td><%= item.getName() %></td>
            <td><%= item.getPrice() %></td>
            <td><%= item.getTotalNumber() %></td>
            <td><%= details != null ? details.getDescription() : "-" %></td>
            <td><%= details != null ? details.getIssueDate() : "-" %></td>
            <td><%= details != null ? details.getExpiryDate() : "-" %></td>
            <td>
                <a href="<%= request.getContextPath() %>/ItemController?action=show-item&id=<%= item.getId() %>">Update</a>
                <a href="<%= request.getContextPath() %>/ItemController?action=remove-item&id=<%= item.getId() %>">Delete</a>
                <% if (details == null) { %>
                    <a href="<%= request.getContextPath() %>/ItemController?action=show-add-details&itemId=<%= item.getId() %>">Add Details</a>
                <% } else { %>
                    <a href="<%= request.getContextPath() %>/ItemController?action=delete-item-details&itemId=<%= item.getId() %>">Delete Details</a>
                <% } %>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>


    <button class="f">
    <a href="<%= request.getContextPath() %>/item/add-item.jsp">Add Item</a>
    </button>


</div>

</body>
</html>