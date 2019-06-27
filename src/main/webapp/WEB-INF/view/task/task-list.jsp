<%@ page import="org.jetbrains.annotations.NotNull" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../header.jsp"/>
<c:set var="tasks" value="${tasks}"/>
<c:set var="projectId" value="${id}"/>
<html>
<head>
    <title>Task list</title>
</head>
<body>

<table class="table table-bordered table-hover" id="table">
    <caption style="text-align: center; caption-side: top">TASK MANAGEMENT</caption>
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">ID</th>
        <th scope="col">Name</th>
        <th scope="col">Description</th>
        <th scope="col">Status</th>
        <th scope="col" colspan="2" style="text-align: center">Options</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="task" items="${tasks}" varStatus="count">
        <tr>
            <td scope="row">${count.index + 1}</td>
            <td scope="row">${task.getId()}</td>
            <td scope="row">${task.getName()}</td>
            <td scope="row">${task.getDescription()}</td>
            <td scope="row">${task.getStatus()}</td>
            <td scope="row" class="project-btn">
                <a href="${pageContext.request.contextPath}/task-edit?id=${task.getId()}">
                    <button type="submit" class="btn btn-outline-warning btn-small">
                        <i class="fa fa-cog"></i>
                    </button>
                </a>
            </td>
            <td scope="row" class="project-btn">
                <a href="${pageContext.request.contextPath}/task-remove?id=${task.getId()}">
                    <button type="submit" class="btn btn-outline-danger btn-small">
                        <i class=" fa fa-trash-o"></i>
                    </button>
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="new-task">
    <a href="${pageContext.request.contextPath}/task-create?id=${projectId}">
        <button type="submit" class="btn btn-outline-success">create new task
        </button>
    </a>
</div>
</body>
</html>
