<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../header.jsp"/>
<c:set var="user" value="${requestScope.user}"/>
<c:set var="userId" value="${requestScope.userId}"/>
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
        <label for="inputEmail">E-mail</label>
        <input type="email" class="form-control" id="inputEmail" placeholder="e-mail" name="email" value="${user.getEmail()}">
    </div>
    <div class="form-group">
        <label for="inputLogin">Login</label>
        <input type="text" class="form-control" id="inputLogin" placeholder="login" name="login" value="${user.getLogin()}">
    </div>
    <div class="form-group">
        <label for="inputPassword">Password</label>
        <input type="password" class="form-control" id="inputPassword" placeholder="password" name="password">
    </div>
    <input type="text" hidden name="userId" value="${userId}">
    <div style="display: flex; justify-content: center;">
        <button type="submit" class="btn btn-outline-primary">Save</button>
    </div>
</form>
</div>
</body>
</html>
