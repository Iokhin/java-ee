package ru.iokhin.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.iokhin.tm.api.service.ITaskService;
import ru.iokhin.tm.model.dto.ProjectDTO;
import ru.iokhin.tm.model.dto.TaskDTO;
import ru.iokhin.tm.model.entity.Project;
import ru.iokhin.tm.model.entity.Task;
import ru.iokhin.tm.model.entity.User;
import ru.iokhin.tm.repository.ProjectRepository;
import ru.iokhin.tm.repository.TaskRepository;
import ru.iokhin.tm.repository.UserRepository;
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
    @Qualifier("taskRepo")
    private TaskRepository taskRepository;

    @NotNull
    @Autowired
    @Qualifier("userRepo")
    private UserRepository userRepository;

    @NotNull
    @Autowired
    @Qualifier("projectRepo")
    private ProjectRepository projectRepository;

    @Override
    public List<TaskDTO> findAllByUserId(@NotNull final String userId) {
        @Nullable final User user = getUser(userId);
        if (user == null) return null;
        return taskRepository.findAllByUser(user).stream().map(Task::getTaskDTO).collect(Collectors.toList());
    }

    @Override
    public void removeAllByUserId(@NotNull final String userId) {
        @Nullable final User user = getUser(userId);
        if (user == null) return;
        taskRepository.removeAllByUser(user);
    }

    @Override
    public List<TaskDTO> sortByUserId(@NotNull String userId, @NotNull String parameter) {
        StringValidator.validate(userId, parameter);
        @Nullable final User user = getUser(userId);
        if (user == null) return null;
        switch (parameter) {
            case "order":
                return findAllByUserId(userId);
            case "status":
                return taskRepository.findAllByUserOrderByStatus(user)
                        .stream().map(Task::getTaskDTO).collect(Collectors.toList());
            case "dateStart":
                return taskRepository.findAllByUserOrderByDateStart(user)
                        .stream().map(Task::getTaskDTO).collect(Collectors.toList());
            case "dateEnd":
                return taskRepository.findAllByUserOrderByDateEnd(user)
                        .stream().map(Task::getTaskDTO).collect(Collectors.toList());
            default:
                throw new IllegalArgumentException("WRONG PARAMENTER");
        }
    }

    @Override
    public List<TaskDTO> findByPartOfNameOrDescription(@NotNull String userId, @NotNull String keyWord) {
        @Nullable final User user = getUser(userId);
        if (user == null) return null;
        return taskRepository.findAllByPartOfNameOrDescription(user, keyWord).stream()
                .map(Task::getTaskDTO).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> findAllByProjectId(@NotNull String userId, @NotNull String projectId) {
        StringValidator.validate(userId, projectId);
        @Nullable final User user = getUser(userId);
        @Nullable final Project project = getProject(projectId);
        if (user == null || project == null) return null;
        return taskRepository.findAllByUserAndProject(user, project).stream()
                .map(Task::getTaskDTO).collect(Collectors.toList());
    }

    @Override
    public TaskDTO findOneByUserId(@NotNull String userId, @NotNull String id) {
        StringValidator.validate(userId, id);
        final User user = getUser(userId);
        if (user == null) return null;
        final Task task = taskRepository.findTaskByUserAndId(user, id).orElse(null);
        if (task == null) return null;
        return task.getTaskDTO();
    }

    @Override
    public void persist(@NotNull TaskDTO taskDTO) {
        @NotNull final Task task = getTaskFromDTO(taskDTO);
        taskRepository.save(task);
    }

    @Override
    public void merge(@NotNull TaskDTO taskDTO) {
        @NotNull final Task task = getTaskFromDTO(taskDTO);
        taskRepository.save(task);
    }

    @Override
    public void removeById(@NotNull final String id) {
        taskRepository.deleteById(id);
    }

    @Override
    public TaskDTO findOne(@NotNull String id) {
        final Task task = taskRepository.findById(id).orElse(null);
        if (task == null) return null;
        return task.getTaskDTO();
    }

    @Override
    public List<TaskDTO> findAll() {
        return taskRepository.findAll().stream().map(Task::getTaskDTO).collect(Collectors.toList());
    }

    private User getUser(@NotNull final String userId) {
        return userRepository.findById(userId).orElse(null);
    }

    private Project getProject(@Nullable final String projectId) {
        if (projectId == null) return null;
        return projectRepository.findById(projectId).orElse(null);
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
