package ru.iokhin.tm.controller.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.iokhin.tm.api.service.IProjectService;
import ru.iokhin.tm.model.entity.Project;
import javax.servlet.http.HttpSession;

@Controller
public class ProjectRemoveController {

    @NotNull
    @Autowired
    private IProjectService projectService;

    @GetMapping("/project-remove")
    public String projectRemove(@RequestParam @Nullable final String id, @NotNull final HttpSession session) throws Exception {
        if (id == null) throw new IllegalArgumentException("PROJECT ID IS NULL");
        @NotNull final String userId = session.getAttribute("userId").toString();
        @Nullable final Project project = projectService.findOneByUserId(userId, id);
        if (project == null) throw new Exception("NO SUCH PROJECT");
        projectService.removeById(project.getId());
        return "redirect:/project-list";
    }
}