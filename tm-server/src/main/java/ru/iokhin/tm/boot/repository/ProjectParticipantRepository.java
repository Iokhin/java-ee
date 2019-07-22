package ru.iokhin.tm.boot.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.iokhin.tm.boot.model.entity.ProjectParticipant;

import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface ProjectParticipantRepository extends JpaRepository<ProjectParticipant, String> {

    @Query("SELECT e FROM ProjectParticipant e WHERE e.project.id = :id")
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<ProjectParticipant> findAllByProjectId(@Param("id") @NotNull String id);

    @Query("SELECT e FROM ProjectParticipant e WHERE e.user.id = :userId AND e.project.id = :projectId")
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<ProjectParticipant> findAllByUserIdAndProjectId(@Param("userId") @NotNull String userId, @Param("projectId") @NotNull String projectId);

}
