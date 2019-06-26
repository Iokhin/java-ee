package ru.iokhin.tm.api.service;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.repositroy.IRepository;
import ru.iokhin.tm.api.repositroy.ITaskRepository;
import ru.iokhin.tm.model.Task;

import java.util.List;

public interface ITaskService extends IRepository<Task> {

    List<Task> findAllByUserId(@NotNull String userId);

    void removeAllByUserId(@NotNull String userId);

    List<Task> sortByUserId(@NotNull String userId, @NotNull String parametr);

    List<Task> findByPartOfNameOrDescription(@NotNull String userId, @NotNull String keyWord);

    List<Task> findAllByProjectId(@NotNull String userId, @NotNull String projectId);

    Task findOneByUserId(@NotNull String userId, @NotNull String id);

}
