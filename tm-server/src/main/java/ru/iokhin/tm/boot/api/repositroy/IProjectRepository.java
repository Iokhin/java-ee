package ru.iokhin.tm.boot.api.repositroy;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.boot.model.entity.Project;
import ru.iokhin.tm.boot.model.entity.User;

import java.util.List;

public interface IProjectRepository extends IRepository<Project> {

    List<Project> findAllByUserId(@NotNull User user);

    void removeAllByUserId(@NotNull User user);

    List<Project> sortByUserId(@NotNull final User user, @NotNull final String parameter);

    List<Project> findByPartOfNameOrDescription(@NotNull final User user, @NotNull final String keyWord);

    Project findOneByUserId(@NotNull final User user, @NotNull final String id);
}
