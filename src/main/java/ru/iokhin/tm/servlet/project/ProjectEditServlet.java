package ru.iokhin.tm.servlet.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.service.IProjectService;
import ru.iokhin.tm.api.service.ISessionService;
import ru.iokhin.tm.enumerated.Status;
import ru.iokhin.tm.exception.AuthException;
import ru.iokhin.tm.model.Project;
import ru.iokhin.tm.service.ProjectService;
import ru.iokhin.tm.service.SessionService;
import ru.iokhin.tm.util.StringValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/project-edit")
public class ProjectEditServlet extends HttpServlet {

    @NotNull
    private final IProjectService projectService = ProjectService.INSTANCE;

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        @Nullable final String projectId = req.getParameter("id");
        @Nullable Project project = null;
        try {
            if (projectId == null) throw new Exception("PROJECT ID IS NULL");
            project = projectService.findOne(projectId);
            req.setAttribute("project", project);
            req.setAttribute("id", projectId);
            req.getRequestDispatcher("/WEB-INF/view/project/project-edit.jsp").forward(req, resp);
        } catch (Exception e) {
            resp.sendError(401, e.getMessage());
        }
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        @NotNull final String projectId = req.getParameter("id");
        @NotNull final Project project = getProject(req);
        projectService.merge(project);
        resp.sendRedirect("/project-list");
    }

    private Project getProject(@NotNull final HttpServletRequest req) {
        @NotNull final String projectId = req.getParameter("id");
        @NotNull final String name = req.getParameter("name");
        @NotNull final String description = req.getParameter("description");
        @Nullable final Status status = Status.getStatusByName(req.getParameter("status"));
        StringValidator.validate(name, description);
        @NotNull final Project project = projectService.findOne(projectId);
        project.setName(name);
        project.setDescription(description);
        project.setStatus(status);
        return project;
    }
}
