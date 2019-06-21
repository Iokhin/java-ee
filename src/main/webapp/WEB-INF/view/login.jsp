<%--
  Created by IntelliJ IDEA.
  User: qoper
  Date: 21.06.19
  Time: 13:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post">
    Login : <input type="text" name="login"><br>
    Password : <input type="password" name="password"><br>
    <input type="submit" value="login">
</form>
</body>
</html>
