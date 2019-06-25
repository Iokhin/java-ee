package ru.iokhin.tm.servlet.project;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.service.IProjectService;
import ru.iokhin.tm.api.service.ISessionService;
import ru.iokhin.tm.exception.AuthException;
import ru.iokhin.tm.model.Project;
import ru.iokhin.tm.service.ProjectService;
import ru.iokhin.tm.service.SessionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/project-list")
public class ProjectListServlet extends HttpServlet {

    @NotNull
    private final IProjectService projectService = ProjectService.INSTANCE;

    @NotNull
    private final ISessionService sessionService = SessionService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        try {
            sessionService.validateSession(session);
            List<Project> projects = projectService.findAllByUserId(session.getAttribute("userId").toString());
            req.setAttribute("projects", projects);
            req.getRequestDispatcher("/WEB-INF/view/project/project-list.jsp").forward(req, resp);
        } catch (AuthException e) {
            resp.sendRedirect("/login");
        }
    }
}
