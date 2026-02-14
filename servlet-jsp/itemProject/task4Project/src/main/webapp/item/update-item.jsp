<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Update Item</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
     <link rel="stylesheet" href="<%= request.getContextPath() %>/item/css/add-item.css">


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
<!-- partial -->

</body>
</html>