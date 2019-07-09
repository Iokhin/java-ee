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
//
//import javax.servlet.http.HttpSession;
//
//@Controller
//public class TaskRemoveController {
//
//    @NotNull
//    @Autowired
//    private ITaskService taskService;
//
//    @GetMapping("/task-remove")
//    public String projectRemove(@RequestParam @Nullable final String id, @NotNull final HttpSession session) throws Exception {
//        if (id == null) throw new IllegalArgumentException("PROJECT ID IS NULL");
//        @NotNull final String userId = session.getAttribute("userId").toString();
//        @Nullable final TaskDTO task = taskService.findOneByUserId(userId, id);
//        if (task == null) throw new Exception("NO SUCH TASK");
//        taskService.removeById(task.getId());
//        return "redirect:/task-list";
//    }
//}
