<%@ page import="ru.iokhin.tm.enumerated.Status" %>
<%@ page import="org.jetbrains.annotations.NotNull" %>
<%@ page import="ru.iokhin.tm.model.Task" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../header.jsp"/>
<html>
<head>
    <title>Task edit</title>
</head>
<c:set var="task" value="${requestScope.task}"/>
<c:set var="projects" value="${requestScope.projects}"/>
<body>
<% Task task = (Task) request.getAttribute("task"); %>
<form action="${pageContext.request.contextPath}/task-edit" method="post" id="project-edit-form"
      style=" display: flex; justify-content: center;">
    <table class="table table-borderless col-md-6">
        <caption style="caption-side: top; text-align: center">TASK EDIT</caption>
        <tr>
            <td>Task Name:</td>
            <td><input class="form-control" name="name" type="text" value="${task.getName()}"></td>
        </tr>
        <tr>
            <td>Task Description:</td>
            <td><input class="form-control" name="description" type="text" value="${task.getDescription()}"/></td>
        </tr>
        <tr>
            <td>Start Date:</td>
            <td><input class="form-control" name="startDate" type="date" value="${task.getDateStart()}"/></td>
        </tr>
        <tr>
            <td>End Date:</td>
            <td><input class="form-control" name="endDate" type="date" value="${task.getDateEnd()}"/></td>
        </tr>
        <tr>
            <td>Status:</td>
            <td>
                <select class="form-control" name="status" id="inputStatus">
                    <%for (@NotNull Status status : Status.values()) {%>
                    <option <%if (status == task.getStatus()) out.print("selected");%>><%=status%>
                    </option>
                    <%}%>
                </select>
            </td>
        </tr>
        <tr>
            <td>Project:</td>
            <td>
                <select class="form-control" name="projectId" id="inputProject">
                    <c:forEach var="project" items="${projects}" varStatus="iterator">
                        <option value="${project.getId()}">
                                ${project.getName()}
                        </option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td><input name="id" type="hidden" value="${task.getId()}"/></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center">
                <button class="btn btn-md btn-outline-info" type="submit">Save</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
