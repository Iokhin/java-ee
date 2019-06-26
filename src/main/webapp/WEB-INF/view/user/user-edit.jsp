<%--
  Created by IntelliJ IDEA.
  User: qoper
  Date: 22.06.19
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../header.jsp"/>
<html>
<head>
    <title>Registration form</title>
</head>
<body>
<br>
<div style="text-align: center">
    <h5>USER EDIT</h5>
</div>
<br>
<div id="login-form">
<form action="${pageContext.request.contextPath}/user-edit" method="post">
    <div class="form-group">
        <label for="inputLogin">New login</label>
        <input type="text" class="form-control" id="inputLogin" placeholder="login" name="login" value="${sessionScope.get("userLogin")}">
    </div>
    <div class="form-group">
        <label for="inputPassword">New password</label>
        <input type="password" class="form-control" id="inputPassword" placeholder="password" name="password">
    </div>
    <div style="display: flex; justify-content: center;">
        <button type="submit" class="btn btn-outline-primary">Save</button>
    </div>
</form>
</div>
</body>
</html>
