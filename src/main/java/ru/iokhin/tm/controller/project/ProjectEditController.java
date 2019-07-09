//package ru.iokhin.tm.controller.project;
//
//import org.jetbrains.annotations.NotNull;
//import org.jetbrains.annotations.Nullable;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import ru.iokhin.tm.api.service.IProjectService;
//import ru.iokhin.tm.enumerated.Status;
//import ru.iokhin.tm.model.dto.ProjectDTO;
//import ru.iokhin.tm.model.entity.Project;
//import ru.iokhin.tm.util.StringValidator;
//
//@Controller
//public class ProjectEditController {
//
//    @NotNull
//    @Autowired
//    private IProjectService projectService;
//
//    @GetMapping("/project-edit")
//    public String showForm(@RequestParam @Nullable final String id, @NotNull Model model) throws Exception {
//        if (id == null) throw new Exception("PROJECT ID IS NULL");
//        @Nullable ProjectDTO project = projectService.findOne(id);
//        model.addAttribute("project", project);
//        model.addAttribute("id", id);
//        return "project/project-edit";
//    }
//
//    @PostMapping("/project-edit")
//    public String projectEdit(@RequestParam @Nullable final String id,
//                              @RequestParam @Nullable final String name,
//                              @RequestParam @Nullable final String description,
//                              @RequestParam @Nullable final String status) {
//        StringValidator.validate(name, description, id, status);
//        @NotNull final ProjectDTO project = projectService.findOne(id);
//        project.setName(name);
//        project.setDescription(description);
//        @Nullable final Status projectStatus = Status.getStatusByName(status);
//        project.setStatus(projectStatus);
//        projectService.merge(project);
//        return "redirect:/project-list";
//    }
//
//}
