<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    if (session.getAttribute("loggedUser") == null) {
        response.sendRedirect(request.getContextPath() + "/user/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Item Details</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/item/css/show-items.css">
    <style>
        .input-data input[type="date"] ~ label,
        .input-data input[type="date"] + label {
            top: 0;
            font-size: 0.85rem;
            color: #3a7bd5;
            font-weight: 600;
        }
    </style>
</head>
<body>

<div class="container">

    <!-- Header -->
    <div class="form-header">
        <div class="text">Add Item Details</div>
    </div>

    <!-- Form -->
    <form action="<%= request.getContextPath() %>/ItemController" method="get"
          onsubmit="return validateForm()" autocomplete="off">

        <div class="form-row">
            <div class="input-data" id="descGroup">
                <label for="description">Description</label>
                <input type="text" name="description" id="description"
                       placeholder="e.g. Latest model with 256GB">
                <span class="input-error" id="descError"></span>
            </div>
        </div>

        <div class="form-row">
            <div class="input-data" id="issueGroup">
                <label for="issueDate">Issue Date</label>
                <input type="date" name="issueDate" id="issueDate">
                <span class="input-error" id="issueError"></span>
            </div>
            <div class="input-data" id="expiryGroup">
                <label for="expiryDate">Expiry Date</label>
                <input type="date" name="expiryDate" id="expiryDate">
                <span class="input-error" id="expiryError"></span>
            </div>
        </div>

        <input type="hidden" name="itemId" value="<%= request.getAttribute("itemId") %>">
        <input type="hidden" name="action" value="add-item-details">
        <input type="submit" value="Add Details" class="button">
    </form>

    <div class="back">
        <a href="<%= request.getContextPath() %>/ItemController">Back To Items</a>
    </div>

</div>

<script>
function showError(groupId, errorId, msg) {
    document.getElementById(groupId).classList.add('has-error');
    document.getElementById(errorId).textContent = msg;
}
function clearErrors() {
    ['descGroup','issueGroup','expiryGroup'].forEach(id => {
        document.getElementById(id).classList.remove('has-error');
    });
}
function validateForm() {
    clearErrors();
    const description = document.getElementById('description').value.trim();
    const issueDate   = document.getElementById('issueDate').value;
    const expiryDate  = document.getElementById('expiryDate').value;
    let valid = true;

    if (description === '') {
        showError('descGroup', 'descError', 'Description is required');
        valid = false;
    }
    if (issueDate === '') {
        showError('issueGroup', 'issueError', 'Issue date is required');
        valid = false;
    }
    if (expiryDate === '') {
        showError('expiryGroup', 'expiryError', 'Expiry date is required');
        valid = false;
    }
    if (issueDate && expiryDate && expiryDate < issueDate) {
        showError('expiryGroup', 'expiryError', 'Expiry date must be after issue date');
        valid = false;
    }
    return valid;
}
</script>
</body>
</html>
