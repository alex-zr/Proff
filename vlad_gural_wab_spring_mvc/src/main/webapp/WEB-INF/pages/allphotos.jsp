<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>com.gmail.vladgural</title>
</head>
<body>
<div align="center">
    <%
        Long id = 0l;
        Map<Long, byte[]> photos;
        photos = (Map<Long, byte[]>) request.getAttribute("map");
    %>
    <form action="/delete_selected" method="post">
        <table style="border: 2px solid black">
            <%for (Map.Entry<Long, byte[]> entry : photos.entrySet()) {%>
            <tr style="border: 2px solid black">
                <td style="border: 2px solid black">
                    ID
                </td>
                <td style="border: 2px solid black">
                    <% id = entry.getKey();
                        out.println(id);%>
                </td>
                <td style="border: 2px solid black">
                    <img src="/photo/<%=id%>" style="width:250px"/>
                </td>
                <td style="border: 2px solid black">
                    <input type="checkbox" name="name<%=id%>" value="<%=id%>">Delete
                </td>
            </tr>
            <%}%>
        </table>
        <% if (!photos.isEmpty()) { %>

        <input type="submit" value="Delete selected"/>

        <% } %>
    </form>
</div>
</body>
</html>
