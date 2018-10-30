<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
    <table border="1">
        <c:forEach items="${models}" var="o">
            <tr>
                <td>${o}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
