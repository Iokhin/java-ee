package ru.iokhin.tm.boot.api.repositroy;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.boot.model.entity.Project;
import ru.iokhin.tm.boot.model.entity.Task;
import ru.iokhin.tm.boot.model.entity.User;

import java.util.List;

public interface ITaskRepository extends IRepository<Task> {

    List<Task> findAllByUserId(@NotNull User user);

    void removeAllByUserId(@NotNull User user);

    List<Task> sortByUserId(@NotNull User user, @NotNull String parameter);

    List<Task> findByPartOfNameOrDescription(@NotNull User user, @NotNull String keyWord);

    Task findOneByUserId(@NotNull User user, @NotNull String id);

    List<Task> findAllByProjectId(@NotNull User user, @NotNull Project project);

}
