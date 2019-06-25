<%--
  Created by IntelliJ IDEA.
  User: qoper
  Date: 21.06.19
  Time: 18:01
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../header.jsp"/>
<html>
<head>
    <title>Create Project</title>
</head>
<body>
<h3 style="text-align: center">TASK CREATE</h3>
<div id="project-create-form">
    <form class="project-create-form" action="${pageContext.request.contextPath}/task-create" method="post">
        <div class="user-input">
            <label for="taskName">Enter a name of task</label>
            <input class="form-control" type="text" id="taskName" name="taskName">
        </div>
        <div class="user-input">
            <label for="taskDescription">Enter a task's description</label>
            <input class="form-control" type="text" id="taskDescription" name="taskDescription">
        </div>
        <div style="text-align: center">
            <button type="submit" class="btn btn-outline-dark">create</button>
        </div>
    </form>
</div>
</body>
</html>
