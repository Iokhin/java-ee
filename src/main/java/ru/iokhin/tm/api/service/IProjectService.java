package ru.iokhin.tm.api.service;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.repositroy.IRepository;
import ru.iokhin.tm.model.dto.ProjectDTO;
import ru.iokhin.tm.model.entity.Project;

import java.util.List;

public interface IProjectService extends IRepository<ProjectDTO> {

    List<ProjectDTO> findAllByUserId(@NotNull String userId);

    void removeAllByUserId(@NotNull String userId);

    List<ProjectDTO> sortByUserId(@NotNull String userId, @NotNull String parameter);

    List<ProjectDTO> findByPartOfNameOrDescription(@NotNull String userId, @NotNull String keyWord);

    Project findOneByUserId(@NotNull String userId, @NotNull String id);

}
