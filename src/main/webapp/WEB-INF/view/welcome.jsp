<%--
  Created by IntelliJ IDEA.
  User: qoper
  Date: 22.06.19
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<h1 class="user-welcome">
   Welcome, <%= request.getSession().getAttribute("userLogin").toString() %>
</h1>
<div style="text-align: center">
    <img src="${pageContext.request.contextPath}/resources/pictures/welcome.png">
</div>
</body>
</html>
