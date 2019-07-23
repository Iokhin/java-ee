package ru.iokhin.tm.controller.project;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.iokhin.tm.api.service.IProjectService;
import ru.iokhin.tm.model.dto.ProjectDTO;
import ru.iokhin.tm.model.entity.Project;
import ru.iokhin.tm.repository.ProjectRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ProjectListController {

    @NotNull
    @Autowired
    private IProjectService projectService;

    @NotNull
    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/project-list")
    protected String projectList(@NotNull final HttpSession session, @NotNull final Model model) {
        List<ProjectDTO> projects = projectService.findAllByUserId(session.getAttribute("userId").toString());
        model.addAttribute("projects", projects);
        return "project/project-list";
    }

    @GetMapping("/project-list-pageable")
    protected String projectListPage(@NotNull final HttpSession session, @NotNull final Model model, @RequestParam("page") int pageIndex,
                                     @RequestParam("size") int pageSize) {
        Page<Project> projects = projectRepository.findAll(PageRequest.of(pageIndex, pageSize));
        model.addAttribute("projects", projects.getContent());
        model.addAttribute("pagesTotal", projects.getTotalPages());
        model.addAttribute("page", projects);
        return "project/project-list-pageable";
    }
}
