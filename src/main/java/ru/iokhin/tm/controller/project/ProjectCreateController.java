package ru.iokhin.tm.controller.project;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import ru.iokhin.tm.api.service.IProjectService;
import ru.iokhin.tm.model.Project;

import javax.servlet.http.HttpSession;

@Controller
public class ProjectCreateController {

    @NotNull
    @Autowired
    private IProjectService projectService;

    @PostMapping("/project-create")
    public String projectCreate(@NotNull HttpSession session) {
        @NotNull final String userId = session.getAttribute("userId").toString();
        @NotNull final Project project = new Project(userId);
        projectService.persist(project);
        return "redirect:/project-list";
    }

}
