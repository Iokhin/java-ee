<%@ page import="ru.iokhin.tm.model.Project" %>
<%@ page import="ru.iokhin.tm.enumerated.Status" %>
<%@ page import="org.jetbrains.annotations.NotNull" %>
<%@ page import="ru.iokhin.tm.util.FieldConstant" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: iokhin
  Date: 24.06.2019
  Time: 12:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../header.jsp"/>
<html>
<head>
    <title>Project edit</title>
</head>
<c:set var="project" value="${requestScope.project}"/>
<body>
<% Project project = (Project) request.getAttribute("project"); %>
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
                <select class="form-control" name="<%=FieldConstant.STATUS%>" id="inputStatus">
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

<%--<form id="project-edit-form">--%>
<%--    <div class="form-group row">--%>
<%--        <label for="inputName3" class="col-sm-2 col-form-label">Name</label>--%>
<%--        <div class="col-sm-10">--%>
<%--            <input type="text" class="form-control" id="inputName3" placeholder="Name">--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div class="form-group row">--%>
<%--        <label for="inputDescription3" class="col-sm-2 col-form-label">Description</label>--%>
<%--        <div class="col-sm-10">--%>
<%--            <input type="text" class="form-control" id="inputDescription3" placeholder="Description">--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div class="form-group row">--%>
<%--        <label for="inputStatus3" class="col-sm-2 col-form-label">Status</label>--%>
<%--        <div class="col-sm-10">--%>
<%--            <select class="form-control" id="inputStatus3">--%>
<%--                <option>PLANNING</option>--%>
<%--                <option>PROCESSING</option>--%>
<%--                <option>READY</option>--%>
<%--            </select>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div class="form-group row">--%>
<%--        <label for="inputDateStart3" class="col-sm-2 col-form-label">Date start</label>--%>
<%--        <div class="col-sm-10">--%>
<%--            <input type="date" id="inputDateStart3" name="dateStart">--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div class="form-group row">--%>
<%--        <label for="inputDateEnd3" class="col-sm-2 col-form-label">Date end</label>--%>
<%--        <div class="col-sm-10">--%>
<%--            <input type="date" id="inputDateEnd3" name="dateEnd">--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div class="form-group row">--%>
<%--        <div class="col-sm-10">--%>
<%--            <button type="submit" class="btn btn-primary">Sign in</button>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</form>--%>
</body>
</html>
