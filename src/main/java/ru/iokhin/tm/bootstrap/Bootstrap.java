package ru.iokhin.tm.bootstrap;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.iokhin.tm.api.service.IProjectService;
import ru.iokhin.tm.api.service.ITaskService;
import ru.iokhin.tm.api.service.IUserService;
import ru.iokhin.tm.enumerated.Role;
import ru.iokhin.tm.model.dto.ProjectDTO;
import ru.iokhin.tm.model.dto.TaskDTO;
import ru.iokhin.tm.model.dto.UserDTO;

import javax.annotation.PostConstruct;

@Component
public class Bootstrap {

    @NotNull
    @Autowired
    private IUserService userService;

    @NotNull
    @Autowired
    private IProjectService projectService;

    @NotNull
    @Autowired
    private ITaskService taskService;


    @PostConstruct
    public void init() {
//        init users
        userService.merge(new UserDTO("58607299-b756-4f72-922d-07e3c9f1448d", "user", "user", Role.USER));
        userService.merge(new UserDTO("ada5b8d2-1181-4db7-b0ac-8430d2fcfa6e", "admin", "admin", Role.ADMIN));

//        init projects
        projectService.merge(new ProjectDTO("58607299-b756-4f72-922d-07e3c9f1448d"));
        projectService.merge(new ProjectDTO("58607299-b756-4f72-922d-07e3c9f1448d"));
        projectService.merge(new ProjectDTO("58607299-b756-4f72-922d-07e3c9f1448d"));
        projectService.merge(new ProjectDTO("ada5b8d2-1181-4db7-b0ac-8430d2fcfa6e"));
        projectService.merge(new ProjectDTO("ada5b8d2-1181-4db7-b0ac-8430d2fcfa6e"));
        projectService.merge(new ProjectDTO("ada5b8d2-1181-4db7-b0ac-8430d2fcfa6e"));

//        init task
        taskService.merge(new TaskDTO("58607299-b756-4f72-922d-07e3c9f1448d"));
        taskService.merge(new TaskDTO("58607299-b756-4f72-922d-07e3c9f1448d"));
        taskService.merge(new TaskDTO("58607299-b756-4f72-922d-07e3c9f1448d"));
        taskService.merge(new TaskDTO("ada5b8d2-1181-4db7-b0ac-8430d2fcfa6e"));
        taskService.merge(new TaskDTO("ada5b8d2-1181-4db7-b0ac-8430d2fcfa6e"));
        taskService.merge(new TaskDTO("ada5b8d2-1181-4db7-b0ac-8430d2fcfa6e"));
    }

}
