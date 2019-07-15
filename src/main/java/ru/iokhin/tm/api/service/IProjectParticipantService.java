package ru.iokhin.tm.api.service;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.model.dto.ProjectParticipantDTO;

import java.util.List;

public interface IProjectParticipantService {

    List<ProjectParticipantDTO> findAll();

    List<ProjectParticipantDTO> findAllByProjectId(@NotNull final String projectId);

    void save(@NotNull ProjectParticipantDTO projectParticipantDTO);

    void delete(@NotNull String projectId, @NotNull String userId);
}
