<%--
  Created by IntelliJ IDEA.
  User: qoper
  Date: 22.06.19
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<html>
<head>
    <title>Registration form</title>
</head>
<body>
<div id="login-form">
<form action="${pageContext.request.contextPath}/register" method="post">
    <div class="form-group">
        <label for="inputLogin">Login</label>
        <input type="text" class="form-control" id="inputLogin" placeholder="login" name="login">
    </div>
    <div class="form-group">
        <label for="inputPassword">Password</label>
        <input type="password" class="form-control" id="inputPassword" placeholder="password" name="password">
    </div>
    <button type="submit" class="btn btn-outline-primary">Submit</button>
</form>
</div>
</body>
</html>
