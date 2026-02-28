<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    if (session.getAttribute("loggedUser") == null) {
        response.sendRedirect(request.getContextPath() + "/user/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Item Details</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/item/css/show-items-new.css">
    
      <style>
        .input-data input[type="date"] ~ label {
            top: 0;
            font-size: 0.85rem;
            color: #3a7bd5;
            font-weight: 600;
        }
    </style>
    
</head>
<body>
<div class="container">
    <div class="text">Add Item Details</div>

    <form action="<%= request.getContextPath() %>/ItemController" method="get" onsubmit="return validateForm()">
        <div class="form-row">
            <div class="input-data">
                <input type="text" required name="description" id="description" placeholder=" ">
                <div class="underline"></div>
                <label>Description</label>
            </div>
        </div>
        <div class="form-row">
            <div class="input-data">
                <input type="date" required name="issueDate" id="issueDate" placeholder=" ">
                <div class="underline"></div>
                <label>Issue Date</label>
            </div>
            <div class="input-data">
                <input type="date" required name="expiryDate" id="expiryDate" placeholder=" ">
                <div class="underline"></div>
                <label>Expiry Date</label>
            </div>
        </div>
        <input type="hidden" name="itemId" value="<%= request.getAttribute("itemId") %>">
        <input type="hidden" name="action" value="add-item-details">
        <input type="submit" value="Add Details" class="button">
    </form>

    <p class="back">
        <a href="<%= request.getContextPath() %>/ItemController">Back To Items</a>
    </p>
</div>

<script>
function validateForm() {
    const description = document.getElementById('description').value.trim();
    const issueDate = document.getElementById('issueDate').value;
    const expiryDate = document.getElementById('expiryDate').value;

    if (description === '') {
        alert('Description is required');
        return false;
    }
    if (issueDate === '') {
        alert('Issue date is required');
        return false;
    }
    if (expiryDate === '') {
        alert('Expiry date is required');
        return false;
    }
    if (expiryDate < issueDate) {
        alert('Expiry date must be after issue date');
        return false;
    }
    return true;
}
</script>
</body>
</html>