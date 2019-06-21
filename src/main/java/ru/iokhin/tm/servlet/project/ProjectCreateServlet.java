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
        req.getRequestDispatcher("/WEB-INF/view/project-create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
//        try {
//            sessionService.validateSession(session);
            System.out.println(req.getParameter("name"));
            System.out.println(req.getParameter("description"));
            System.out.println(req.getSession().getAttribute("userId").toString());
//        } catch (AuthException e) {
//            resp.sendError(401, e.getMessage());
//        }
    }

    private Project getProject(HttpServletRequest req) {
        @NotNull final HttpSession session = req.getSession();
        @NotNull final String name = req.getParameter("name");
        @NotNull final String description = req.getParameter("description");
        @NotNull final String userId = session.getAttribute("userId").toString();
        Project project = new Project();
        project.setName(req.getParameter("name"));
        project.setDescription(req.getParameter("description"));
        project.setUserId(((User)session.getAttribute("userId")).getId());
        return project;
    }
}
