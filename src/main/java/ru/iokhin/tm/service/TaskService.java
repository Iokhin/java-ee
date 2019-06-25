package ru.iokhin.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.repositroy.ITaskRepository;
import ru.iokhin.tm.api.service.ITaskService;
import ru.iokhin.tm.model.Task;
import ru.iokhin.tm.repository.TaskRepository;
import ru.iokhin.tm.util.ComparatorUtil;
import ru.iokhin.tm.util.StringValidator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public enum TaskService implements ITaskService {

    INSTANCE;

    @NotNull
    private final ITaskRepository taskRepository = TaskRepository.INSTANCE;

    @Override
    public List<Task> findAllByUserId(@NotNull String userId) {
        return taskRepository.findAllByUserId(userId);
    }

    @Override
    public void removeAllByUserId(@NotNull String userId) {
        taskRepository.removeAllByUserId(userId);
    }

    @Override
    public List<Task> sortByUserId(@NotNull String userId, @NotNull String parameter) {
        StringValidator.validate(userId, parameter);
        if ("order".equals(parameter)) return findAllByUserId(userId);
        @Nullable final Comparator<Task> comparator = ComparatorUtil.getTaskComparator(parameter);
        if (comparator == null) return null;
        return taskRepository.sortByUserId(userId, comparator);
    }

    @Override
    public List<Task> findByPartOfNameOrDescription(@NotNull String userId, @NotNull String keyWord) {
        return taskRepository.findByPartOfNameOrDescription(userId, keyWord);
    }

    @Override
    public List<Task> findAllByProjectId(@NotNull String userId, @NotNull String projectId) {
        @NotNull final List<Task> tasks = new ArrayList<>();
        for (Task task : findAllByUserId(userId)) {
            if (projectId.equals(task.getProjectId()))
                tasks.add(task);
        }
        return tasks;
    }

    @Override
    public Task persist(@NotNull Task entity) {
        return taskRepository.persist(entity);
    }

    @Override
    public Task merge(@NotNull Task entity) {
        return taskRepository.merge(entity);
    }

    @Override
    public Task remove(@NotNull Task entity) {
        return taskRepository.remove(entity);
    }

    @Override
    public Task findOne(@NotNull String id) {
        return taskRepository.findOne(id);
    }

    @Override
    public Collection<Task> findAll() {
        return taskRepository.findAll();
    }
}
