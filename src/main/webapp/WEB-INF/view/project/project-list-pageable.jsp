<%@ page import="ru.iokhin.tm.model.entity.Project" %>
<%@ page import="org.springframework.data.domain.Page" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../header.jsp"/>
<c:set var="projects" value="${requestScope.projects}"/>
<%Page<Project> projects = (Page<Project>) request.getAttribute("page");%>
<%String pageSize = request.getParameter("size");%>
<html>
<head>
    <title>Project list</title>
</head>
<body>
<div class=" d-flex justify-content-end">
    <div class="col-lg-10">
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
                        <a href="${pageContext.request.contextPath}/task-list/by-project?id=${project.getId()}">
                            <button type="submit" class="btn btn-outline-success btn-small">
                                <i class="fa fa-calendar-check-o"></i>
                            </button>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="col-lg-1">
        <select class="form-control" name="pageSizeChose"
                onchange="this.options[this.selectedIndex].value && (window.location = this.options[this.selectedIndex].value);">
            <option value="">page size</option>
            <option value="${pageContext.request.contextPath}/project-list-pageable?page=0&size=1">1</option>
            <option value="${pageContext.request.contextPath}/project-list-pageable?page=0&size=10">10</option>
            <option value="${pageContext.request.contextPath}/project-list-pageable?page=0&size=25">25</option>
            <option value="${pageContext.request.contextPath}/project-list-pageable?page=0&size=50">50</option>
        </select>
    </div>
</div>
<form action="${pageContext.request.contextPath}/project-create" method="post" id="project-create-btn">
    <button type="submit" class="btn btn-outline-success">create new project
    </button>
</form>
<nav style="display: flex; justify-content: center;">
    <ul class="pagination">
        <li class="page-item  <%=projects.getNumber() == 0 ? "disabled" : ""%>">
            <a class="page-link"
               href="${pageContext.request.contextPath}/project-list-pageable?page=<%=projects.getNumber()-1%>&size=<%=pageSize%>">Previous</a>
        </li>
        <li class="page-item <%=projects.getNumber() > 2 ? "" : "d-none"%>"><a class="page-link"
                                                                               href="${pageContext.request.contextPath}/project-list-pageable?page=0&size=<%=pageSize%>">...</a>
        </li>
        <li class="page-item <%=(projects.getNumber() - 2 <= projects.getTotalPages()) && (projects.getNumber() - 2 >= 0) ? "" : "d-none"%>">
            <a class="page-link"
               href="${pageContext.request.contextPath}/project-list-pageable?page=<%=projects.getNumber()-2%>&size=<%=pageSize%>"><%= projects.getNumber() - 1%>
            </a></li>
        <li class="page-item <%=(projects.getNumber() - 1 <= projects.getTotalPages()) && (projects.getNumber() - 1 >= 0) ? "" : "d-none"%>">
            <a class="page-link"
               href="${pageContext.request.contextPath}/project-list-pageable?page=<%=projects.getNumber()-1%>&size=<%=pageSize%>"><%= projects.getNumber()%>
            </a></li>
        <li class="page-item <%=projects.getNumber() <= projects.getTotalPages() ? "active" : "d-none"%>">
            <a class="page-link"
               href="${pageContext.request.contextPath}/project-list-pageable?page=<%=projects.getNumber()%>&size=<%=pageSize%>"><%= projects.getNumber() + 1%>
            </a></li>
        <li class="page-item <%=projects.getNumber() + 1 < projects.getTotalPages() ? "" : "d-none"%>">
            <a class="page-link"
               href="${pageContext.request.contextPath}/project-list-pageable?page=<%=projects.getNumber()+1%>&size=<%=pageSize%>"><%= projects.getNumber() + 2%>
            </a></li>
        <li class="page-item <%=projects.getNumber() + 2 < projects.getTotalPages() ? "" : "d-none"%>">
            <a class="page-link"
               href="${pageContext.request.contextPath}/project-list-pageable?page=<%=projects.getNumber()+2%>&size=<%=pageSize%>"><%= projects.getNumber() + 3%>
            </a></li>
        <li class="page-item <%=projects.getTotalPages() - projects.getNumber() > 3 ? "" : "d-none"%>"><a
                class="page-link"
                href="${pageContext.request.contextPath}/project-list-pageable?page=<%=projects.getTotalPages()-1%>&size=<%=pageSize%>">...</a>
        </li>
        <li class="page-item <%=projects.getNumber() < projects.getTotalPages() - 1 ? "" : "disabled"%>">
            <a class="page-link"
               href="${pageContext.request.contextPath}/project-list-pageable?page=<%=projects.getNumber()+1%>&size=<%=pageSize%>">Next</a>
        </li>
    </ul>
</nav>
</body>
</html>
