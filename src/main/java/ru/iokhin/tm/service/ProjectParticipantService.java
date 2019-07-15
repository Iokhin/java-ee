package ru.iokhin.tm.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.iokhin.tm.api.service.IProjectParticipantService;
import ru.iokhin.tm.model.dto.ProjectParticipantDTO;
import ru.iokhin.tm.model.entity.Project;
import ru.iokhin.tm.model.entity.ProjectParticipant;
import ru.iokhin.tm.model.entity.User;
import ru.iokhin.tm.repository.ProjectParticipantRepository;
import ru.iokhin.tm.repository.ProjectRepository;
import ru.iokhin.tm.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProjectParticipantService implements IProjectParticipantService {

    @Autowired
    private ProjectParticipantRepository projectParticipantRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public List<ProjectParticipantDTO> findAll() {
        return projectParticipantRepository.findAll().stream().map(ProjectParticipant::getProjectParticipantDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectParticipantDTO> findAllByProjectId(@NotNull final String projectId) {
        return projectParticipantRepository.findAllByProjectId(projectId).stream().map(ProjectParticipant::getProjectParticipantDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void save(@NotNull ProjectParticipantDTO projectParticipantDTO) {
        final ProjectParticipant projectParticipant = getEntity(projectParticipantDTO);
        projectParticipantRepository.save(projectParticipant);
    }

    @Override
    public void delete(@NotNull String userId, @NotNull String projectId) {
        final List<ProjectParticipant> projectParticipants = projectParticipantRepository.findAllByUserIdAndProjectId(userId, projectId);
        if (projectParticipants == null) return;
        projectParticipants.forEach(projectParticipantRepository::delete);
    }

    User getUser(@NotNull final String userId) {
        return userRepository.findById(userId).orElse(null);
    }

    Project getProject(@NotNull final String projectId) {
        return projectRepository.findById(projectId).orElse(null);
    }

    ProjectParticipant getEntity(@NotNull final ProjectParticipantDTO projectParticipantDTO) {
        final ProjectParticipant projectParticipant = new ProjectParticipant();
        projectParticipant.setId(projectParticipantDTO.getId());
        projectParticipant.setUser(getUser(projectParticipantDTO.getUserId()));
        projectParticipant.setProject(getProject(projectParticipantDTO.getProjectId()));
        projectParticipant.setUsername(projectParticipantDTO.getUsername());
        return projectParticipant;
    }
}
