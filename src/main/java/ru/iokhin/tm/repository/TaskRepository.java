package ru.iokhin.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.iokhin.tm.model.entity.Project;
import ru.iokhin.tm.model.entity.Task;
import ru.iokhin.tm.model.entity.User;

import java.util.Optional;

@Repository
public interface TaskRepository extends CrudRepository<Task, String> {

    Optional<Task> findTaskByUserAndId(@NotNull User user, @NotNull String id);

    Iterable<Task> findAllByUser(@NotNull User user);

    Iterable<Task> findAllByUserAndProject(@NotNull User user, @NotNull Project project);

    void removeAllByUserAndProject(@NotNull User user, @NotNull Project project);

    void removeAllByUser(@NotNull User user);

    Iterable<Task> findAllByNameContainsOrDescriptionContains(@Nullable String name, @Nullable String description);

    Iterable<Task> findAllByUserOrderByStatus(@NotNull User user);

    Iterable<Task> findAllByUserOrderByDateStart(@NotNull User user);

    Iterable<Task> findAllByUserOrderByDateEnd(@NotNull User user);

}
