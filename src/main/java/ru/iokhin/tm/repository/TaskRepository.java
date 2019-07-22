package ru.iokhin.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.iokhin.tm.model.entity.Project;
import ru.iokhin.tm.model.entity.Task;
import ru.iokhin.tm.model.entity.User;

import java.util.List;
import java.util.Optional;

@Repository("taskRepo")
public interface TaskRepository extends JpaRepository<Task, String> {

    Optional<Task> findTaskByUserAndId(@NotNull User user, @NotNull String id);

    List<Task> findAllByUser(@NotNull User user);

    List<Task> findAllByUserAndProject(@NotNull User user, @NotNull Project project);

    void removeAllByUserAndProject(@NotNull User user, @NotNull Project project);

    void removeAllByUser(@NotNull User user);

    @Query("SELECT e FROM Task e WHERE user = :user AND e.name LIKE CONCAT('%', :keyWord, '%') or e.description LIKE CONCAT('%', :keyWord, '%')")
    List<Task> findAllByPartOfNameOrDescription(@Param("user") @NotNull User user, @Param("keyWord") @NotNull String keyWord);

    List<Task> findAllByUserOrderByStatus(@NotNull User user);

    List<Task> findAllByUserOrderByDateStart(@NotNull User user);

    List<Task> findAllByUserOrderByDateEnd(@NotNull User user);

    @Override
    List<Task> findAll();

    @Override
    Page<Task> findAll(Pageable pageable);
}
