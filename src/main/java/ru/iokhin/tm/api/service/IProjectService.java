package ru.iokhin.tm.api.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.repositroy.IRepository;
import ru.iokhin.tm.model.Project;
import ru.iokhin.tm.util.StringValidator;

import java.util.List;

public interface IProjectService extends IRepository<Project> {

    List<Project> findAllByUserId(@NotNull String userId);

    void removeAllByUserId(@NotNull String userId);

    List<Project> sortByUserId(@NotNull String userId, @NotNull String parameter);

    List<Project> findByPartOfNameOrDescription(@NotNull String userId, @NotNull String keyWord);

    Project findOneById(@NotNull String userId, @NotNull String id);

}
