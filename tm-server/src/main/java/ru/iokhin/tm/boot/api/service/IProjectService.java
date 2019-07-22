package ru.iokhin.tm.boot.api.service;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.boot.api.repositroy.IRepository;
import ru.iokhin.tm.boot.model.dto.ProjectDTO;

import java.util.List;

public interface IProjectService extends IRepository<ProjectDTO> {

    List<ProjectDTO> findAllByUserId(@NotNull String userId);

    void removeAllByUserId(@NotNull String userId);

    List<ProjectDTO> sortByUserId(@NotNull String userId, @NotNull String parameter);

    List<ProjectDTO> findByPartOfNameOrDescription(@NotNull String userId, @NotNull String keyWord);

    ProjectDTO findOneByUserId(@NotNull String userId, @NotNull String id);

}
