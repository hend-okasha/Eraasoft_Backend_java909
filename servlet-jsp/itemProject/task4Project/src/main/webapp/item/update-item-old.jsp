<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
  <title>Update Item</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
     <link rel="stylesheet" href="<%= request.getContextPath() %>/item/css/show-items-new.css">


</head>
<body>
<!-- partial:index.partial.html -->
<div class="container">
  <div class="text">
    Update Item
  </div>
  <form action="/ItemService/ItemController" method="get">

    <div class="form-row">
      <div class="input-data">
        <input type="text" required name="name" value="${item.name}" >
        <div class="underline"></div>
        <label>Name</label>
      </div>
      <div class="input-data">
        <input type="text" required name="price" value="${item.price}" >
        <div class="underline"></div>
        <label>PRICE</label>
      </div>
    </div>
    <div class="form-row">
      <div class="input-data">
        <input type="text" required name="totalNumber" value="${item.totalNumber}">
        <div class="underline"></div>
        <label>TOTAL_NUMBER</label>
      </div>
	<input type="hidden"  name="id" value="${item.id}">
	 <input type="hidden" required name="action" value = "update-item">
    </div>
    <input type="submit" value="Update" class="button">
  </form>

  <p class="back">
    <a href="/ItemService/ItemController" >Back To Items</a>
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
        if (isNaN(totalNumber) || totalNumber < 0) {
            alert('Total number must be 0 or more');
            return false;
        }
        return true;
    }
</script>
<!-- partial -->

</body>
</html>