<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Item List</title>
</head>
<body>
<p><strong>Table with scriptlets:</strong></p>
<table border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
    </tr>
    <%
        Map<Integer, String> products = (Map<Integer, String>) request.getAttribute("products");
        for (Map.Entry<Integer, String> product : products.entrySet()) {
            out.println("<tr><td>" + product.getKey() + "</td><td>" + product.getValue() + "</td></tr>");
        }
    %>
</table>

<p><strong>Table with JSTL:</strong></p>
<table border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
    </tr>
    <c:forEach var="product" items="${products}">
        <tr>
            <td><c:out value="${product.key}"/></td>
            <td><c:out value="${product.value}"/></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
