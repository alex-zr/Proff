<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.time.LocalDate" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Page</title>
</head>
<body>
<%!
    public void method() {

    }
%>
<h1 style="color:red">Hello User from proff</h1>
<img src="../duke.running.gif" width=""/>
<%
    String name = (String) request.getAttribute("name");
    out.println(LocalDate.now() + name);
    method();
%>
<%= 4 + 4 %>
<c:out value="${2}"></c:out>
<ol>
    <c:forEach items="${models}" var="model">
        <li>${model}</li>
    </c:forEach>
</ol>


<form action="/serv" method="post">
    <input type="text" name="name1" value="Stepan"> <br/>
    <input type="password" name="name2" value="Kurgan"> <br/>
    <input type="submit">
</form>
</body>
</html>
