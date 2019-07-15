package ru.iokhin.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.iokhin.tm.model.entity.Project;
import ru.iokhin.tm.model.entity.ProjectParticipant;

import java.util.List;

@Repository
public interface ProjectParticipantRepository extends JpaRepository<ProjectParticipant, String> {

    @Query("SELECT e FROM ProjectParticipant e WHERE e.project.id = :id")
    List<ProjectParticipant> findAllByProjectId(@Param("id") @NotNull String id);

    @Query("SELECT e FROM ProjectParticipant e WHERE e.user.id = :userId AND e.project.id = :projectId")
    List<ProjectParticipant> findAllByUserIdAndProjectId(@Param("userId") @NotNull String userId, @Param("projectId") @NotNull String projectId);

}
