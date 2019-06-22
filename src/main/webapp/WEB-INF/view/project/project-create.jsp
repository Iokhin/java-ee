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
<div id="project-create-form">
    <form class="project-create-form" action="${pageContext.request.contextPath}/project-create" method="post">
        <div class="user-input">
            <label for="projectName">Enter a name of project</label>
            <input class="form-control" type="text" id="projectName" name="projectName" placeholder="project name">
        </div>
        <div class="user-input">
            <label for="projectDescription">Enter a project's description</label>
            <input class="form-control" type="text" id="projectDescription" name="projectDescription" placeholder="project description">
        </div>
        <button type="submit" class="btn btn-outline-dark">create</button>
    </form>
</div>
</body>
</html>
