package ru.iokhin.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.iokhin.tm.model.entity.Project;
import ru.iokhin.tm.model.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends CrudRepository<Project, String> {

    Optional<Project> findProjectByUserAndId(@NotNull User user, @NotNull String id);

    List<Project> findAllByUser(@NotNull User user);

    void removeAllByUser(@NotNull User user);

    @Query("SELECT e FROM Project e WHERE user = :user AND e.name LIKE CONCAT('%', :keyWord, '%') or e.description LIKE CONCAT('%', :keyWord, '%')")
    List<Project> findAllByPartOfNameOrDescription(@Param ("user") @NotNull User user, @Param("keyWord") @NotNull String keyWord);

    List<Project> findAllByUserOrderByStatus(@NotNull User user);

    List<Project> findAllByUserOrderByDateStart(@NotNull User user);

    List<Project> findAllByUserOrderByDateEnd(@NotNull User user);

    @Override
    List<Project> findAll();
}
