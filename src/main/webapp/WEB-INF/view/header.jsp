<%--
  Created by IntelliJ IDEA.
  User: qoper
  Date: 22.06.19
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        @import "${pageContext.request.contextPath}/resources/css/style.css";
    </style>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
          integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#"><img src="${pageContext.request.contextPath}/resources/pictures/tm-symbol.jpg"
                                          width="40" height="40"></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText"
            aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarText">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/welcome">Home<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/project-list">Projects</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/task-list">Tasks</a>
            </li>
        </ul>
        <span class="nav-item">
            <c:if test="${sessionScope.userId == null}">
                <a class="nav-link" href="${pageContext.request.contextPath}/login">Log in</a>
            </c:if>
            <c:if test="${sessionScope.userId != null}">
                <a class="nav-link" href="${pageContext.request.contextPath}/logout">Log out</a>
            </c:if>
            </span>
        <span class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath}/register">Register</a>
        </span>
    </div>
</nav>
</body>
</html>
