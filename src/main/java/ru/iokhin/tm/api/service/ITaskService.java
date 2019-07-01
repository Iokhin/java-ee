package ru.iokhin.tm.api.service;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.repositroy.IRepository;
import ru.iokhin.tm.model.dto.TaskDTO;

import java.util.List;

public interface ITaskService extends IRepository<TaskDTO> {

    List<TaskDTO> findAllByUserId(@NotNull String userId);

    void removeAllByUserId(@NotNull String userId);

    List<TaskDTO> sortByUserId(@NotNull String userId, @NotNull String parametr);

    List<TaskDTO> findByPartOfNameOrDescription(@NotNull String userId, @NotNull String keyWord);

    List<TaskDTO> findAllByProjectId(@NotNull String userId, @NotNull String projectId);

    TaskDTO findOneByUserId(@NotNull String userId, @NotNull String id);

}
