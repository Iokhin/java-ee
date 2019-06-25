package ru.iokhin.tm.servlet.task;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.service.IProjectService;
import ru.iokhin.tm.api.service.ISessionService;
import ru.iokhin.tm.api.service.ITaskService;
import ru.iokhin.tm.service.ProjectService;
import ru.iokhin.tm.service.SessionService;
import ru.iokhin.tm.service.TaskService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(urlPatterns = "/task-remove")
public class TaskRemoveServlet extends HttpServlet {

    @NotNull
    private final ISessionService sessionService = SessionService.INSTANCE;

    @NotNull
    private final ITaskService taskService = TaskService.INSTANCE;

    @NotNull
    private final IProjectService projectService = ProjectService.INSTANCE;

}
