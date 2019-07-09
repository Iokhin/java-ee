//package ru.iokhin.tm.controller.task;
//
//import org.jetbrains.annotations.NotNull;
//import org.jetbrains.annotations.Nullable;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import ru.iokhin.tm.api.service.ITaskService;
//import ru.iokhin.tm.model.dto.TaskDTO;
//import ru.iokhin.tm.model.entity.Task;
//
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpSession;
//
//@Controller
//public class TaskCreateFromProjectController extends HttpServlet {
//
//    @NotNull
//    @Autowired
//    private ITaskService taskService;
//
//    @GetMapping("/task-create/from-project")
//    public String taskCreate(@RequestParam(required = false) @Nullable String id,
//                             @NotNull final HttpSession session) {
//        @NotNull final String userId = session.getAttribute("userId").toString();
//        @NotNull final TaskDTO task = new TaskDTO(userId);
//        task.setProjectId(id);
//        taskService.persist(task);
//        return "redirect:/task-list/by-project?id=" + id;
//    }
//}
