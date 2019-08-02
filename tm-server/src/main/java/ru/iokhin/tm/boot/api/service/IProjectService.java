package ru.iokhin.tm.boot.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.boot.api.repositroy.IRepository;
import ru.iokhin.tm.boot.enumerated.Status;
import ru.iokhin.tm.boot.model.dto.ProjectDTO;

import java.text.ParseException;
import java.util.List;

public interface IProjectService extends IRepository<ProjectDTO> {

    void create(@NotNull String userId);

    List<ProjectDTO> findAllByUserId(@NotNull String userId);

    void removeAllByUserId(@NotNull String userId);

    List<ProjectDTO> sortByUserId(@NotNull String userId, @NotNull String parameter);

    List<ProjectDTO> findByPartOfNameOrDescription(@NotNull String userId, @NotNull String keyWord);

    ProjectDTO findOneByUserId(@NotNull String userId, @NotNull String id);

    void edit(@NotNull String id, @Nullable String newName, @Nullable String newDescription,
              @Nullable String newStatus, @Nullable String newDateStart, @Nullable String newDateEnd) throws ParseException;

}
