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
            Long id=0l;
            Map<Long, byte[]> photos;
            photos = (Map<Long, byte[]>)request.getAttribute("map");
        %>
        <form action="/delete_selected" method="post">
            <table>
                <%for(Map.Entry<Long, byte[]> entry: photos.entrySet()){%>
                <tr>
                    <td>
                        ID
                    </td>
                    <td>
                        <%  id = entry.getKey();
                            out.println(id);%>
                    </td>
                    <td>
                        <img src="/photo/<%=id%>"  style="width:250px"/>
                    </td>
                    <td>
                        <input type="checkbox" name="name<%=id%>" value="<%=id%>">Delete
                    </td>
                </tr>
                <%}%>
            </table>
            <input type="submit" value="Delete selected"/>
        </form>
    </div>
</body>
</html>
