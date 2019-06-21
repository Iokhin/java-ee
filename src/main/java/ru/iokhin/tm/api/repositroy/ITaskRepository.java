package ru.iokhin.tm.api.repositroy;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.model.Task;

import java.util.Comparator;
import java.util.List;

public interface ITaskRepository extends IRepository<Task> {

    List<Task> findAllByUserId(@NotNull String userId);

    void removeAllByUserId(@NotNull String userId);

    List<Task> sortByUserId(@NotNull final String userId, @NotNull final Comparator<Task> comparator);

    List<Task> findByPartOfNameOrDescription(@NotNull final String userId, @NotNull final String keyWord);

}
