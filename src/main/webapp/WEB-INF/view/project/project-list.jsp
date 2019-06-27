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

<table class="table table-bordered table-hover" id="table">
    <caption style="text-align: center; caption-side: top">PROJECT MANAGEMENT</caption>
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">ID</th>
        <th scope="col">Name</th>
        <th scope="col">Description</th>
        <th scope="col">Status</th>
        <th scope="col" colspan="3" style="text-align: center">Options</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="project" items="${projects}" varStatus="count">
        <tr>
            <td scope="row">${count.index + 1}</td>
            <td scope="row">${project.getId()}</td>
            <td scope="row">${project.getName()}</td>
            <td scope="row">${project.getDescription()}</td>
            <td scope="row">${project.getStatus()}</td>
            <td scope="row" class="project-btn">
                <a href="${pageContext.request.contextPath}/project-edit?id=${project.getId()}">
                    <button type="submit" class="btn btn-outline-warning btn-small">
                        <i class="fa fa-cog"></i>
                    </button>
                </a>
            </td>
            <td scope="row" class="project-btn">
                <a href="${pageContext.request.contextPath}/project-remove?id=${project.getId()}">
                    <button type="submit" class="btn btn-outline-danger btn-small">
                        <i class="fa fa-trash-o"></i>
                    </button>
                </a>
            </td>
            <td scope="row" class="project-btn">
                <a href="${pageContext.request.contextPath}/task-list?id=${project.getId()}">
                    <button type="submit" class="btn btn-outline-success btn-small">
                        <i class="fa fa-calendar-check-o"></i>
                    </button>
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<form action="${pageContext.request.contextPath}/project-create" method="post" id="project-create-btn">
    <button type="submit" class="btn btn-outline-success">create new project
    </button>
</form>
</body>
</html>
