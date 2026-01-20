<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Items List</title>
</head>
<body>
<h2>All Items</h2>
<ul>
    <c:forEach var="item" items="${allItems}">
        <li>${item}</li>
    </c:forEach>
</ul>

<h2>Items with ID 1 or 2</h2>
<ul>
    <c:forEach var="item" items="${byId}">
        <li>${item}</li>
    </c:forEach>
</ul>

<h2>Items where Name contains 'i'</h2>
<ul>
    <c:forEach var="item" items="${byName}">
        <li>${item}</li>
    </c:forEach>
</ul>

<h2>Items where Price > 50 OR < 20</h2>
<ul>
    <c:forEach var="item" items="${byPrice}">
        <li>${item}</li>
    </c:forEach>
</ul>
</body>
</html>