package ru.iokhin.tm.servlet.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.service.IProjectService;
import ru.iokhin.tm.api.service.ISessionService;
import ru.iokhin.tm.api.service.ITaskService;
import ru.iokhin.tm.model.Task;
import ru.iokhin.tm.service.ProjectService;
import ru.iokhin.tm.service.SessionService;
import ru.iokhin.tm.service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/task-remove")
public class TaskRemoveServlet extends HttpServlet {

    @NotNull
    private final ITaskService taskService = TaskService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String taskId = req.getParameter("id");
        if (taskId == null) resp.sendError(401, "TASK ID IS NULL");
        @NotNull final String userId = req.getSession().getAttribute("userId").toString();
        @Nullable final Task task = taskService.findOneByUserId(userId, taskId);
        if (task == null) resp.sendError(401, "NO SUCH TASK");
        taskService.remove(task);
        resp.sendRedirect("/task-list");
    }
}
