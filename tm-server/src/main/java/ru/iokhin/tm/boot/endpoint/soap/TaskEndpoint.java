package ru.iokhin.tm.boot.endpoint.soap;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import ru.iokhin.tm.boot.api.endpoint.ITaskEndpoint;
import ru.iokhin.tm.boot.api.service.ITaskService;
import ru.iokhin.tm.boot.model.dto.TaskDTO;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "ru.iokhin.tm.boot.api.endpoint.ITaskEndpoint")
public class TaskEndpoint implements ITaskEndpoint {

    @Autowired
    private ITaskService taskService;

    @Override
    public List<TaskDTO> findAllByUserId(@NotNull String userId) {
        return taskService.findAllByUserId(userId);
    }

    @Override
    public void removeAllByUserId(@NotNull String userId) {
        taskService.removeAllByUserId(userId);
    }

    @Override
    public List<TaskDTO> sortByUserId(@NotNull String userId, @NotNull String parametr) {
        return taskService.sortByUserId(userId, parametr);
    }

    @Override
    public List<TaskDTO> findByPartOfNameOrDescription(@NotNull String userId, @NotNull String keyWord) {
        return taskService.findByPartOfNameOrDescription(userId, keyWord);
    }

    @Override
    public List<TaskDTO> findAllByProjectId(@NotNull String userId, @NotNull String projectId) {
        return taskService.findAllByProjectId(userId, projectId);
    }

    @Override
    public TaskDTO findOneByUserId(@NotNull String userId, @NotNull String id) {
        return taskService.findOneByUserId(userId, id);
    }

    @Override
    public void create(@NotNull String userId) {
        taskService.create(userId);
    }

    @Override
    public void persist(@NotNull TaskDTO entity) {
        taskService.persist(entity);
    }

    @Override
    public void merge(@NotNull TaskDTO entity) {
        taskService.merge(entity);
    }

    @Override
    public void removeById(@NotNull String id) {
        taskService.removeById(id);
    }

    @Override
    public TaskDTO findOne(@NotNull String id) {
        return taskService.findOne(id);
    }

    @Override
    public List<TaskDTO> findAll() {
        return taskService.findAll();
    }
}
