<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: qoper
  Date: 21.06.19
  Time: 18:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Project</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/project-create" method="post">
    Enter a name of project : <input type="text" name="name"><br>
    Enter a project's description : <input type="text" name="description" size="40">
    <input type="submit" value="create">
</form>
</body>
</html>
