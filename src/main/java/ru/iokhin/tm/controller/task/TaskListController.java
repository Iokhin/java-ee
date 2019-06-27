package ru.iokhin.tm.controller.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.iokhin.tm.api.service.ITaskService;
import ru.iokhin.tm.model.Task;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TaskListController {

    @NotNull
    @Autowired
    private ITaskService taskService;

    @GetMapping("/task-list")
    protected String taskList(@RequestParam(required = false) @Nullable final String id,
            @NotNull final HttpSession session, @NotNull final Model model) {
        @NotNull final String userId = session.getAttribute("userId").toString();
        List<Task> tasks = (id == null || id.isEmpty()) ? taskService.findAllByUserId(userId) :
                taskService.findAllByProjectId(userId, id);
        model.addAttribute("tasks", tasks);
        model.addAttribute("id", id);
        return "task/task-list";
    }
}
