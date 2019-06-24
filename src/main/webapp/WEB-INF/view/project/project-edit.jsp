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
<form action="${pageContext.request.contextPath}/project-edit" method="post" id="project-edit-form">
    <table class="table">
        <caption style="caption-side: top">PROJECT EDIT</caption>
        <tr>
            <td>Project Name:</td>
            <td><input name="name" type="text" value="${project.getName()}"></td>
        </tr>
        <tr>
            <td>Project Description:</td>
            <td><input name="description" type="text" value="${project.getDescription()}"/></td>
        </tr>
        <tr>
            <td>Start Date:</td>
            <td><input name="startDate" type="date" value="${project.getDateStart()}"/></td>
        </tr>
        <tr>
            <td>End Date:</td>
            <td><input name="endDate" type="date" value="${project.getDateEnd()}"/></td>
        </tr>
        <tr>
            <td>Status:</td>
            <td><input name="status" type="text" value="${project.getStatus()}"/></td>
        </tr>
        <tr>
            <td><input name="id" type="hidden" value="${project.getId()}"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <button type="submit">Save</button>
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
