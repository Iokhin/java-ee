<%@ page import="ru.iokhin.tm.enumerated.Status" %>
<%@ page import="org.jetbrains.annotations.NotNull" %>
<%@ page import="ru.iokhin.tm.model.dto.ProjectDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../header.jsp"/>
<html>
<head>
    <title>Project edit</title>
</head>
<% ProjectDTO project = (ProjectDTO) request.getAttribute("project"); %>
<c:set var="project" value="${requestScope.project}"/>
<body>
<form action="${pageContext.request.contextPath}/project-edit" method="post" id="project-edit-form"
      style=" display: flex; justify-content: center;">
    <table class="table table-borderless col-md-6">
        <caption style="caption-side: top; text-align: center">PROJECT EDIT</caption>
        <tr>
            <td>Project Name:</td>
            <td><input class="form-control" name="name" type="text" value="${project.getName()}"></td>
        </tr>
        <tr>
            <td>Project Description:</td>
            <td><input class="form-control" name="description" type="text" value="${project.getDescription()}"/></td>
        </tr>
        <tr>
            <td>Start Date:</td>
            <td><input class="form-control" name="startDate" type="date" value="${project.getDateStart()}"/></td>
        </tr>
        <tr>
            <td>End Date:</td>
            <td><input class="form-control" name="endDate" type="date" value="${project.getDateEnd()}"/></td>
        </tr>
        <tr>
            <td>Status:</td>
            <td>
                <select class="form-control" name="status" id="inputStatus">
                    <%for (@NotNull Status status : Status.values()) {%>
                    <option <%if (status == project.getStatus()) out.print("selected");%>><%=status%>
                    </option>
                    <%}%>
                </select>
            </td>
        </tr>
        <tr>
            <td><input name="id" type="hidden" value="${project.getId()}"/></td>
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
