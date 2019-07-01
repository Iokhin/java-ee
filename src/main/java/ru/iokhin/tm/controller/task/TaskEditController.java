package ru.iokhin.tm.controller.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.iokhin.tm.api.service.IProjectService;
import ru.iokhin.tm.api.service.ITaskService;
import ru.iokhin.tm.enumerated.Status;
import ru.iokhin.tm.model.dto.ProjectDTO;
import ru.iokhin.tm.model.dto.TaskDTO;
import ru.iokhin.tm.util.StringValidator;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TaskEditController {

    @NotNull
    @Autowired
    private ITaskService taskService;

    @NotNull
    @Autowired
    private IProjectService projectService;

    @GetMapping("/task-edit")
    public String showForm(@RequestParam @Nullable final String id, @NotNull Model model,
                           @NotNull HttpSession session) throws Exception {
        if (id == null) throw new Exception("TASK ID IS NULL");
        @Nullable final TaskDTO task = taskService.findOne(id);
        @NotNull final String userId = session.getAttribute("userId").toString();
        @Nullable final List<ProjectDTO> projects = projectService.findAllByUserId(userId);
        model.addAttribute("task", task);
        model.addAttribute("id", id);
        model.addAttribute("projects", projects);
        return "task/task-edit";
    }

    @PostMapping("/task-edit")
    public String projectEdit(@RequestParam @Nullable final String id,
                              @RequestParam @Nullable final String name,
                              @RequestParam @Nullable final String description,
                              @RequestParam @Nullable final String status,
                              @RequestParam @Nullable final String projectId) {
        StringValidator.validate(name, description, id, status);
        @NotNull final TaskDTO task = taskService.findOne(id);
        task.setName(name);
        task.setDescription(description);
        @Nullable final Status projectStatus = Status.getStatusByName(status);
        task.setStatus(projectStatus);
        task.setProjectId(projectId);
        taskService.merge(task);
        return "redirect:/task-list";
    }

}
