package ru.iokhin.tm.api.repositroy;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.model.Project;

import java.util.Comparator;
import java.util.List;

public interface IProjectRepository extends IRepository<Project> {

    List<Project> findAllByUserId(@NotNull String userId);

    void removeAllByUserId(@NotNull String userId);

    List<Project> sortByUserId(@NotNull final String userId, @NotNull final Comparator<Project> comparator);

    List<Project> findByPartOfNameOrDescription(@NotNull final String userId, @NotNull final String keyWord);

}
