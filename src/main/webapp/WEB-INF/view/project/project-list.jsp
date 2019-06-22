<%--
  Created by IntelliJ IDEA.
  User: qoper
  Date: 21.06.19
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../header.jsp"/>
<c:set var="projects" value="${requestScope.projects}"/>
<html>
<head>
    <title>Project list</title>
</head>
<body>
<table class="entityTable">
    <caption>Projects</caption>
    <thead>
    <tr>
        <th>№</th>
        <th>Id</th>
        <th>Name</th>
        <th>Description</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="project" items="${projects}" varStatus="count">
    <tr>
        <td>${count.index + 1}</td>
        <td>${project.getId()}</td>
        <td>${project.getName()}</td>
        <td>${project.getDescription()}</td>
    </tr>
    </c:forEach>
    </tbody>
</table>
<form action="${pageContext.request.contextPath}/project-create" method="get">
    <button type="submit" class="btn btn-outline-success">create new project
    </button>
</form>
</body>
</html>
