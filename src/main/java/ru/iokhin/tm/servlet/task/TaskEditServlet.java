package ru.iokhin.tm.servlet.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import ru.iokhin.tm.api.service.IProjectService;
import ru.iokhin.tm.api.service.ISessionService;
import ru.iokhin.tm.api.service.ITaskService;
import ru.iokhin.tm.enumerated.Status;
import ru.iokhin.tm.model.Task;
import ru.iokhin.tm.service.ProjectService;
import ru.iokhin.tm.service.SessionService;
import ru.iokhin.tm.service.TaskService;
import ru.iokhin.tm.util.StringValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/task-edit")
public class TaskEditServlet extends HttpServlet {

    @NotNull
    @Autowired
    private ITaskService taskService;

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        @Nullable final String taskId = req.getParameter("id");
        @Nullable Task task = null;
        try {
            if (taskId == null) throw new Exception("TASK'S ID IS NULL");
            task = taskService.findOne(taskId);
            req.setAttribute("task", task);
            req.setAttribute("id", taskId);
            req.getRequestDispatcher("/WEB-INF/view/task/task-edit.jsp").forward(req, resp);
        } catch (Exception e) {
            resp.sendError(401, e.getMessage());
        }
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        @NotNull final String taskId = req.getParameter("id");
        @NotNull final Task task = getTask(req);
        taskService.merge(task);
        resp.sendRedirect("/task-list");
    }

    private Task getTask(@NotNull final HttpServletRequest req) {
        @NotNull final String taskId = req.getParameter("id");
        @NotNull final String projectId = req.getParameter("projectId");
        @NotNull final String name = req.getParameter("name");
        @NotNull final String description = req.getParameter("description");
        @Nullable final Status status = Status.getStatusByName(req.getParameter("status"));
        StringValidator.validate(name, description, taskId);
        @NotNull final Task task = taskService.findOne(taskId);
        task.setName(name);
        task.setDescription(description);
        task.setStatus(status);
        task.setProjectId(projectId);
        return task;
    }

}
