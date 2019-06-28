package ru.iokhin.tm.controller.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.iokhin.tm.api.service.ITaskService;
import ru.iokhin.tm.model.Task;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

@Controller
public class TaskCreateController extends HttpServlet {

    @NotNull
    @Autowired
    private ITaskService taskService;

    @GetMapping("/task-create")
    public String taskCreate(@RequestParam(required = false) @Nullable String id,
                             @NotNull final HttpSession session) {
        @NotNull final String userId = session.getAttribute("userId").toString();
        @NotNull final Task task = new Task(userId);
        task.setProjectId(id);
        taskService.persist(task);
        return "redirect:/task-list";
    }
}
