package ru.iokhin.tm.bootstrap;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.iokhin.tm.api.service.IProjectService;
import ru.iokhin.tm.api.service.IRoleService;
import ru.iokhin.tm.api.service.ITaskService;
import ru.iokhin.tm.api.service.IUserService;
import ru.iokhin.tm.enumerated.RoleEnum;
import ru.iokhin.tm.model.dto.ProjectDTO;
import ru.iokhin.tm.model.dto.TaskDTO;
import ru.iokhin.tm.model.dto.UserDTO;
import ru.iokhin.tm.model.entity.Role;
import ru.iokhin.tm.repository.RoleRepository;

import javax.annotation.PostConstruct;

@Component
public class Bootstrap {

    @NotNull
    @Autowired
    @Qualifier("userService")
    private IUserService userService;

    @NotNull
    @Autowired
    @Qualifier("projectService")
    private IProjectService projectService;

    @NotNull
    @Autowired
    @Qualifier("taskService")
    private ITaskService taskService;

    @NotNull
    @Autowired
    private IRoleService roleService;


    @PostConstruct
    public void init() {
        cleanup();
//        init roles
        roleService.save(new Role(RoleEnum.ADMIN.toString()));
        roleService.save(new Role(RoleEnum.USER.toString()));

//        init users
        userService.merge(new UserDTO("58607299-b756-4f72-922d-07e3c9f1448d", "user", "user", RoleEnum.USER));
        userService.merge(new UserDTO("ada5b8d2-1181-4db7-b0ac-8430d2fcfa6e", "admin", "admin", RoleEnum.ADMIN));

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

    public void cleanup() {
        roleService.deleteAll();
    }

}
