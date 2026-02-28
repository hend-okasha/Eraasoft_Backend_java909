<%@page import="item.model.ItemDetails"%>
<%@page import="item.model.Item"%>
<%@page import="item.model.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    if (session.getAttribute("loggedUser") == null) {
        response.sendRedirect(request.getContextPath() + "/item/login.jsp");
        return;
    }
    User loggedUser = (User) session.getAttribute("loggedUser");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Items</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/item/css/show-items.css">
</head>
<body>

<%
    String message = (String) session.getAttribute("message");
    if (message != null) {
        session.removeAttribute("message");
%>
<div class="toast toast-success" id="toast" onclick="closeToast()">
    <span class="toast-icon">✅</span>
    <span><%= message %></span>
    <span class="toast-close">✕</span>
</div>
<% } %>

<div class="layer">

    <!-- Header -->
    <div class="layer-header">
        <h1>Items</h1>
        <div class="user-info">
            <span>Welcome, <strong><%= loggedUser.getName() %></strong></span>
            <a href="<%= request.getContextPath() %>/UserController?action=logout" class="btn-logout">Logout</a>
        </div>
    </div>

    <!-- Table -->
    <div class="table-wrapper">
        <table>
            <thead>
            <tr>
                <th>#</th>
                <th>Name</th>
                <th>Price</th>
                <th>Stock</th>
                <th>Description</th>
                <th>Issue Date</th>
                <th>Expiry Date</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<Item> items = (List<Item>) request.getAttribute("allItems");
                List<ItemDetails> allDetails = (List<ItemDetails>) request.getAttribute("allDetails");

                for (Item item : items) {
                    ItemDetails details = null;
                    if (allDetails != null) {
                        for (ItemDetails d : allDetails) {
                            if (d.getItemId().equals(item.getId())) {
                                details = d;
                                break;
                            }
                        }
                    }

                    // Stock level class
                    String stockClass = "stock-high";
                    if (item.getTotalNumber() <= 5) stockClass = "stock-low";
                    else if (item.getTotalNumber() <= 20) stockClass = "stock-medium";
            %>
            <tr>
                <td><%= item.getId() %></td>
                <td><strong><%= item.getName() %></strong></td>
                <td><span class="price-badge"><%= item.getPrice() %></span></td>
                <td><span class="stock-badge <%= stockClass %>"><%= item.getTotalNumber() %></span></td>
                <td><%= details != null ? details.getDescription() : "<span class='no-data'>—</span>" %></td>
                <td><%= details != null ? details.getIssueDate() : "<span class='no-data'>—</span>" %></td>
                <td><%= details != null ? details.getExpiryDate() : "<span class='no-data'>—</span>" %></td>
                <td>
                    <div class="action-cell">
                        <a href="<%= request.getContextPath() %>/ItemController?action=show-item&id=<%= item.getId() %>" class="btn btn-update">✏️ Update</a>
                        <a href="<%= request.getContextPath() %>/ItemController?action=remove-item&id=<%= item.getId() %>" class="btn btn-delete">🗑️ Delete</a>
                        <% if (details == null) { %>
                            <a href="<%= request.getContextPath() %>/ItemController?action=show-add-details&itemId=<%= item.getId() %>" class="btn btn-add-details">➕ Add Details</a>
                        <% } else { %>
                            <a href="<%= request.getContextPath() %>/ItemController?action=delete-item-details&itemId=<%= item.getId() %>" class="btn btn-delete-details">🗑️ Delete Details</a>
                        <% } %>
                    </div>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>

    <!-- Footer -->
    <div class="table-footer">
        <a href="<%= request.getContextPath() %>/item/add-item.jsp" class="btn-add-item">Add Item</a>
    </div>

</div>

<script>
function closeToast() {
    const toast = document.getElementById('toast');
    if (toast) {
        toast.classList.add('hide');
        setTimeout(() => toast.remove(), 300);
    }
}
setTimeout(() => {
    const toast = document.getElementById('toast');
    if (toast) {
        toast.classList.add('hide');
        setTimeout(() => toast.remove(), 300);
    }
}, 4000);
</script>

</body>
</html>
