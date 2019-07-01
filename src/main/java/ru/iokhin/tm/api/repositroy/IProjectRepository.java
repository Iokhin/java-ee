package ru.iokhin.tm.api.repositroy;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.model.entity.Project;
import ru.iokhin.tm.model.entity.User;

import java.util.Comparator;
import java.util.List;

public interface IProjectRepository extends IRepository<Project> {

    List<Project> findAllByUserId(@NotNull User user);

    void removeAllByUserId(@NotNull User user);

    List<Project> sortByUserId(@NotNull final User user, @NotNull final String parameter);

    List<Project> findByPartOfNameOrDescription(@NotNull final User user, @NotNull final String keyWord);

    Project findOneByUserId(@NotNull final User user, @NotNull final String id);
}
