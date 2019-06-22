<%--
  Created by IntelliJ IDEA.
  User: qoper
  Date: 21.06.19
  Time: 13:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>

<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="login-form">
<form action="${pageContext.request.contextPath}/login" method="post">
    <div class="user-input">
    <input class="form-control" type="text" name="login" placeholder="login">
    </div>
    <div class="user-input">
    <input class="form-control" type="password" name="password" placeholder="password">
    </div>
    <div class="login-btn">
        <button type="submit" class="btn btn-outline-danger btn-sm">login</button>
    </div>
</form>
</div>
</body>
</html>

<jsp:include page="footer.jsp"/>