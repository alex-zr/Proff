<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><h3>View contact</h3></head>
<body>

<div class="container">
    <div>
        name:${contact.name}
    </div>
    <div>
        surname:${contact.surname}
    </div>
    <div>
        phone:${contact.phone}
    </div>
    <div>
        email:${contact.email}
    </div>
    <div>
        status:
        <a href="/activate/${contact.id}">${contact.status == '1' ? 'disable' : 'enable'}</a>
    </div>


</div>

</body>

</html>