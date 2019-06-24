package ru.iokhin.tm.servlet.project;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.service.IProjectService;
import ru.iokhin.tm.api.service.ISessionService;
import ru.iokhin.tm.api.service.IUserService;
import ru.iokhin.tm.exception.AuthException;
import ru.iokhin.tm.model.Project;
import ru.iokhin.tm.model.User;
import ru.iokhin.tm.service.ProjectService;
import ru.iokhin.tm.service.SessionService;
import ru.iokhin.tm.service.UserService;
import ru.iokhin.tm.util.StringValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/project-create")
public class ProjectCreateServlet extends HttpServlet {

    @NotNull
    private final ISessionService sessionService = SessionService.INSTANCE;

    @NotNull
    private final IProjectService projectService = ProjectService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/project/project-create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        try {
            sessionService.validateSession(session);
            Project project = getProject(req);
            projectService.persist(project);
            resp.sendRedirect("/project-list");
        } catch (AuthException e) {
            resp.sendRedirect("/login");
        }
    }

    private Project getProject(HttpServletRequest req) {
        @NotNull final HttpSession session = req.getSession();
        @NotNull final String name = req.getParameter("projectName");
        @NotNull final String description = req.getParameter("projectDescription");
        @NotNull final String userId = session.getAttribute("userId").toString();
        StringValidator.validate(name, description, userId);
        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setUserId(userId);
        return project;
    }
}
