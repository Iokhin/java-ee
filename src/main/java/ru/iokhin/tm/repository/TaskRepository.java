package ru.iokhin.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.iokhin.tm.model.entity.Project;
import ru.iokhin.tm.model.entity.Task;
import ru.iokhin.tm.model.entity.User;

import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

@Repository("taskRepo")
public interface TaskRepository extends JpaRepository<Task, String> {

    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    Optional<Task> findTaskByUserAndId(@NotNull User user, @NotNull String id);

    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<Task> findAllByUser(@NotNull User user);

    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<Task> findAllByUserAndProject(@NotNull User user, @NotNull Project project);

    void removeAllByUserAndProject(@NotNull User user, @NotNull Project project);

    void removeAllByUser(@NotNull User user);

    @Query("SELECT e FROM Task e WHERE user = :user AND e.name LIKE CONCAT('%', :keyWord, '%') or e.description LIKE CONCAT('%', :keyWord, '%')")
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<Task> findAllByPartOfNameOrDescription(@Param("user") @NotNull User user, @Param("keyWord") @NotNull String keyWord);

    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<Task> findAllByUserOrderByStatus(@NotNull User user);

    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<Task> findAllByUserOrderByDateStart(@NotNull User user);

    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<Task> findAllByUserOrderByDateEnd(@NotNull User user);

    @Override
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<Task> findAll();
}
