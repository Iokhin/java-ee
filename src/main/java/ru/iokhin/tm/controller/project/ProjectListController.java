//package ru.iokhin.tm.controller.project;
//
//import org.jetbrains.annotations.NotNull;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import ru.iokhin.tm.api.service.IProjectService;
//import ru.iokhin.tm.model.dto.ProjectDTO;
//import ru.iokhin.tm.model.entity.Project;
//import javax.servlet.http.HttpSession;
//import java.util.List;
//
//@Controller
//public class ProjectListController {
//
//    @NotNull
//    @Autowired
//    private IProjectService projectService;
//
//    @GetMapping("/project-list")
//    protected String projectList(@NotNull final HttpSession session, @NotNull final Model model) {
//        List<ProjectDTO> projects = projectService.findAllByUserId(session.getAttribute("userId").toString());
//        model.addAttribute("projects", projects);
//        return "project/project-list";
//    }
//}
