//package ru.iokhin.tm.servlet.task;
//
//import org.jetbrains.annotations.NotNull;
//import org.springframework.beans.factory.annotation.Autowired;
//import ru.iokhin.tm.api.service.IProjectService;
//import ru.iokhin.tm.api.service.ISessionService;
//import ru.iokhin.tm.api.service.ITaskService;
//import ru.iokhin.tm.exception.AuthException;
//import ru.iokhin.tm.model.Task;
//import ru.iokhin.tm.service.ProjectService;
//import ru.iokhin.tm.service.SessionService;
//import ru.iokhin.tm.service.TaskService;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet(urlPatterns = "/task-list")
//public class TaskListServlet extends HttpServlet {
//
//    @NotNull
//    @Autowired
//    private ITaskService taskService;
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        @NotNull final String projectId = req.getParameter("id");
//        @NotNull final String userId = session.getAttribute("userId").toString();
//        List<Task> tasks = (projectId == null || projectId.isEmpty()) ? taskService.findAllByUserId(userId) : taskService
//                .findAllByProjectId(userId, projectId);
//        req.setAttribute("tasks", tasks);
//        req.setAttribute("id", projectId);
//        req.getRequestDispatcher("/WEB-INF/view/task/task-list.jsp").forward(req, resp);
//    }
//}
