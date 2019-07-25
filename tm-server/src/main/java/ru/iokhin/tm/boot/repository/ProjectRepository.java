package ru.iokhin.tm.boot.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import ru.iokhin.tm.boot.model.entity.Project;
import ru.iokhin.tm.boot.model.entity.User;

import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

@RepositoryRestResource(path = "projectRepo", collectionResourceRel = "projects", itemResourceRel = "project")
@Repository("projectRepo")
public interface ProjectRepository extends JpaRepository<Project, String> {

    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    Optional<Project> findProjectByUserAndId(@NotNull User user, @NotNull String id);

    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<Project> findAllByUser(@NotNull User user);

    void removeAllByUser(@NotNull User user);

    @Query("SELECT e FROM Project e WHERE user = :user AND e.name LIKE CONCAT('%', :keyWord, '%') or e.description LIKE CONCAT('%', :keyWord, '%')")
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<Project> findAllByPartOfNameOrDescription(@Param("user") @NotNull User user, @Param("keyWord") @NotNull String keyWord);

    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<Project> findAllByUserOrderByStatus(@NotNull User user);

    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<Project> findAllByUserOrderByDateStart(@NotNull User user);

    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<Project> findAllByUserOrderByDateEnd(@NotNull User user);

    @Override
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<Project> findAll();


}
