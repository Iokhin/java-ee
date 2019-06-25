<%--
  Created by IntelliJ IDEA.
  User: qoper
  Date: 21.06.19
  Time: 18:01
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.iokhin.tm.model.Project" %>
<%@ page import="ru.iokhin.tm.util.FieldConstant" %>
<%@ page import="ru.iokhin.tm.util.DateFormatter" %>
<%@ page import="ru.iokhin.tm.enumerated.Status" %>
<%@ page import="org.jetbrains.annotations.NotNull" %>
<jsp:include page="../header.jsp"/>
<html>
<head>
    <title>Create Project</title>
</head>
<body>
<h3 style="text-align: center">PROJECT CREATE</h3>
<div id="project-create-form">
    <form class="project-create-form" action="${pageContext.request.contextPath}/project-create" method="post">
        <div class="user-input">
            <label for="projectName">Enter a name of project</label>
            <input class="form-control" type="text" id="projectName" name="projectName">
        </div>
        <div class="user-input">
            <label for="projectDescription">Enter a project's description</label>
            <input class="form-control" type="text" id="projectDescription" name="projectDescription">
        </div>
        <div style="text-align: center">
            <button type="submit" class="btn btn-outline-dark">create</button>
        </div>
    </form>
</div>
</body>
</html>
