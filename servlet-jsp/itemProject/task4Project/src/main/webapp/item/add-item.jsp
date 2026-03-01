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
    <title>Add Item</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/item/css/show-items.css">
</head>
<body>


<%
    String message = (String) request.getAttribute("message");
    if (message != null) {
%>
<div class="toast toast-error" id="toast" onclick="closeToast()">
    <span class="toast-icon">❌</span>
    <span><%= message %></span>
    <span class="toast-close">✕</span>
</div>
<% } %>

<div class="container">

    <!-- Header -->
    <div class="form-header">
        <div class="text">Add Item</div>
    </div>

    <!-- Form -->
    <form action="<%= request.getContextPath() %>/ItemController" method="get"
          onsubmit="return validateForm()" autocomplete="off">

        <div class="form-row">
            <div class="input-data" id="nameGroup">
                <label for="name">Item Name</label>
                <input type="text" name="name" id="name" placeholder="e.g. iPhone 15">
                <span class="input-error" id="nameError"></span>
            </div>
            <div class="input-data" id="priceGroup">
                <label for="price">Price ($)</label>
                <input type="number" name="price" id="price" step="0.01" min="0.01" placeholder="e.g. 999.99">
                <span class="input-error" id="priceError"></span>
            </div>
        </div>

        <div class="form-row">
            <div class="input-data" id="totalGroup">
                <label for="totalNumber">Total Number</label>
                <input type="number" name="totalNumber" id="totalNumber" min="0" placeholder="e.g. 50">
                <span class="input-error" id="totalError"></span>
            </div>
        </div>

        <input type="hidden" name="action" value="add-item">
        <input type="submit" value="Add Item" class="button">
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
    ['nameGroup','priceGroup','totalGroup'].forEach(id => {
        document.getElementById(id).classList.remove('has-error');
    });
}
function validateForm() {
    clearErrors();
    const name        = document.getElementById('name').value.trim();
    const price       = parseFloat(document.getElementById('price').value);
    const totalNumber = parseInt(document.getElementById('totalNumber').value);
    let valid = true;

    if (name.length < 2) {
        showError('nameGroup', 'nameError', 'Name must be at least 2 characters');
        valid = false;
    }
    if (isNaN(price) || price <= 0) {
        showError('priceGroup', 'priceError', 'Price must be greater than 0');
        valid = false;
    }
    if (isNaN(totalNumber) || totalNumber < 0) {
        showError('totalGroup', 'totalError', 'Total number must be 0 or more');
        valid = false;
    }
    return valid;
}

function closeToast() {
    const toast = document.getElementById('toast');
    if (toast) {
        toast.classList.add('hide');
        setTimeout(() => toast.remove(), 300);
    }
}
setTimeout(closeToast, 4000);
</script>
</body>
</html>
