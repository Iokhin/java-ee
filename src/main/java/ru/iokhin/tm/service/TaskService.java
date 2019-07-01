package ru.iokhin.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.iokhin.tm.api.repositroy.IProjectRepository;
import ru.iokhin.tm.api.repositroy.ITaskRepository;
import ru.iokhin.tm.api.repositroy.IUserRepository;
import ru.iokhin.tm.api.service.ITaskService;
import ru.iokhin.tm.model.dto.ProjectDTO;
import ru.iokhin.tm.model.dto.TaskDTO;
import ru.iokhin.tm.model.entity.Project;
import ru.iokhin.tm.model.entity.Task;
import ru.iokhin.tm.model.entity.User;
import ru.iokhin.tm.util.StringValidator;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service(TaskService.NAME)
public class TaskService implements ITaskService {

    @NotNull
    public static final String NAME = "taskService";

    @NotNull
    @Autowired
    private ITaskRepository taskRepository;

    @NotNull
    @Autowired
    private IUserRepository userRepository;

    @NotNull
    @Autowired
    private IProjectRepository projectRepository;

    @Override
    public List<TaskDTO> findAllByUserId(@NotNull String userId) {
        @Nullable final User user = getUser(userId);
        if (user == null) return null;
        return taskRepository.findAllByUserId(user).stream().map(Task::getTaskDTO).collect(Collectors.toList());
    }

    @Override
    public void removeAllByUserId(@NotNull String userId) {
        @Nullable final User user = getUser(userId);
        if (user == null) return;
        taskRepository.removeAllByUserId(user);
    }

    @Override
    public List<TaskDTO> sortByUserId(@NotNull String userId, @NotNull String parameter) {
        StringValidator.validate(userId, parameter);
        @Nullable final User user = getUser(userId);
        if (user == null) return null;
        if ("order".equals(parameter)) return findAllByUserId(userId);
        return taskRepository.sortByUserId(user, parameter).stream()
                .map(Task::getTaskDTO).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> findByPartOfNameOrDescription(@NotNull String userId, @NotNull String keyWord) {
        @Nullable final User user = getUser(userId);
        if (user == null) return null;
        return taskRepository.findByPartOfNameOrDescription(user, keyWord).stream()
                .map(Task::getTaskDTO).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> findAllByProjectId(@NotNull String userId, @NotNull String projectId) {
        StringValidator.validate(userId, projectId);
        @Nullable final User user = getUser(userId);
        @Nullable final Project project = getProject(projectId);
        if (user == null || project == null) return null;
        return taskRepository.findAllByProjectId(user, project).stream()
                .map(Task::getTaskDTO).collect(Collectors.toList());
    }

    @Override
    public TaskDTO findOneByUserId(@NotNull String userId, @NotNull String id) {
        StringValidator.validate(userId, id);
        final User user = getUser(userId);
        if (user == null) return null;
        return taskRepository.findOneByUserId(user, id).getTaskDTO();
    }

    @Override
    public void persist(@NotNull TaskDTO taskDTO) {
        @NotNull final Task task = getTaskFromDTO(taskDTO);
        taskRepository.persist(task);
    }

    @Override
    public void merge(@NotNull TaskDTO taskDTO) {
        @NotNull final Task task = getTaskFromDTO(taskDTO);
        taskRepository.merge(task);
    }

    @Override
    public void removeById(@NotNull final String id) {
        taskRepository.removeById(id);
    }

    @Override
    public TaskDTO findOne(@NotNull String id) {
        final Task task = taskRepository.findOne(id);
        if (task == null) return null;
        return task.getTaskDTO();
    }

    @Override
    public List<TaskDTO> findAll() {
        return taskRepository.findAll().stream().map(Task::getTaskDTO).collect(Collectors.toList());
    }

    private User getUser(@NotNull final String userId) {
        return userRepository.findOne(userId);
    }

    private Project getProject(@Nullable final String projectId) {
        if (projectId == null) return null;
        return projectRepository.findOne(projectId);
    }

    private Project getProjectFromDTO(@NotNull final ProjectDTO projectDTO) {
        final Project project = new Project();
        project.setId(projectDTO.getId());
        project.setUser(getUser(projectDTO.getUserId()));
        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        project.setDateStart(projectDTO.getDateStart());
        project.setDateEnd(projectDTO.getDateEnd());
        project.setStatus(projectDTO.getStatus());
        return project;
    }

    private Task getTaskFromDTO(@NotNull final TaskDTO taskDTO) {
        final Task task = new Task();
        task.setId(taskDTO.getId());
        task.setUser(getUser(taskDTO.getUserId()));
        task.setProject(getProject(taskDTO.getProjectId()));
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setDateStart(taskDTO.getDateStart());
        task.setDateEnd(taskDTO.getDateEnd());
        task.setStatus(taskDTO.getStatus());
        return task;
    }

}
