package ru.iokhin.tm.servlet.task;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.service.IProjectService;
import ru.iokhin.tm.api.service.ISessionService;
import ru.iokhin.tm.api.service.ITaskService;
import ru.iokhin.tm.exception.AuthException;
import ru.iokhin.tm.model.Task;
import ru.iokhin.tm.service.ProjectService;
import ru.iokhin.tm.service.SessionService;
import ru.iokhin.tm.service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/task-create")
public class TaskCreateServlet extends HttpServlet {

    @NotNull
    private final ITaskService taskService = TaskService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Task task = new Task();
        task.setName("NEW TASK");
        task.setDescription("NEW DESCRIPTION");
        task.setUserId(session.getAttribute("userId").toString());
        @NotNull final String projectId = req.getParameter("id");
        task.setProjectId(projectId);
        taskService.persist(task);
        resp.sendRedirect("/task-list?id=" + projectId);
//            req.getRequestDispatcher("/WEB-INF/view/task/task-list.jsp").forward(req, resp);
    }
}
