package ru.iokhin.tm.servlet.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.service.IProjectService;
import ru.iokhin.tm.api.service.ISessionService;
import ru.iokhin.tm.model.Project;
import ru.iokhin.tm.service.ProjectService;
import ru.iokhin.tm.service.SessionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/project-remove")
public class ProjectRemoveServlet extends HttpServlet {

    @NotNull
    private final IProjectService projectService = ProjectService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        @Nullable final String id = req.getParameter("id");
        if (id == null) resp.sendError(401, "PROJECT ID IS NULL");
        @NotNull final String userId = req.getSession().getAttribute("userId").toString();
        @Nullable final Project project = projectService.findOneById(userId, id);
        if (project == null) resp.sendError(401, "NO SUCH PROJECT");
        projectService.remove(project);
        resp.sendRedirect("/project-list");
    }
}