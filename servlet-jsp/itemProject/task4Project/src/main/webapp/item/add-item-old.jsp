<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ADD Item</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/item/css/show-items-new.css">
</head>
<body>
<div class="container">
    <div class="text">Add Item</div>
    
    <form action="<%= request.getContextPath() %>/ItemController" method="get" onsubmit="return validateForm()">
        <div class="form-row">
            <div class="input-data">
                <input type="text" required name="name" id="name" minlength="2" maxlength="100">
                <div class="underline"></div>
                <label>Name</label>
            </div>
            <div class="input-data">
                <input type="number" required name="price" id="price" step="0.01" min="0.01">
                <div class="underline"></div>
                <label>PRICE</label>
            </div>
        </div>
        <div class="form-row">
            <div class="input-data">
                <input type="number" required name="totalNumber" id="totalNumber" min="1">
                <div class="underline"></div>
                <label>TOTAL NUMBER</label>
            </div>
        </div>
        <input type="hidden" name="action" value="add-item">
        <input type="submit" value="Add" class="button">
    </form>

    <p class="back">
        <a href="<%= request.getContextPath() %>/ItemController">Back To Items</a>
    </p>
</div>

<script> 
    <%
        String message = (String) request.getAttribute("message");
        if (message != null) {
    %>
        alert('<%= message %>');
    <%
        }
    %>
   
    function validateForm() {
        const name = document.getElementById('name').value.trim();
        const price = parseFloat(document.getElementById('price').value);
        const totalNumber = parseInt(document.getElementById('totalNumber').value);

        if (name === '' || name.length < 2) {
            alert('Name must be at least 2 characters');
            return false;
        }

        if (isNaN(price) || price <= 0) {
            alert('Price must be greater than 0');
            return false;
        }

        if (isNaN(totalNumber) || totalNumber < 1) {
            alert('Total number must be at least 1');
            return false;
        }

        return true;
    }
</script>
</body>
</html> */