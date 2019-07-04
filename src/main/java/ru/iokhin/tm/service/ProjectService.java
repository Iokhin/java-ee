package ru.iokhin.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.iokhin.tm.api.service.IProjectService;
import ru.iokhin.tm.model.dto.ProjectDTO;
import ru.iokhin.tm.model.entity.Project;
import ru.iokhin.tm.model.entity.User;
import ru.iokhin.tm.repository.ProjectRepository;
import ru.iokhin.tm.repository.UserRepository;
import ru.iokhin.tm.util.StringValidator;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service(ProjectService.NAME)
public class ProjectService implements IProjectService {

    @NotNull
    public static final String NAME = "projectService";

    @NotNull
    @Autowired
    @Qualifier("projectRepo")
    private ProjectRepository projectRepository;

    @NotNull
    @Autowired
    @Qualifier("userRepo")
    private UserRepository userRepository;

    @Override
    public List<ProjectDTO> findAllByUserId(@NotNull final String userId) {
        StringValidator.validate(userId);
        final User user = getUser(userId);
        if (user == null) return null;
        return projectRepository.findAllByUser(user).stream()
                .map(Project::getProjectDTO).collect(Collectors.toList());
    }

    @Override
    public void removeAllByUserId(@NotNull final String userId) {
        StringValidator.validate(userId);
        final User user = getUser(userId);
        if (user == null) return;
        projectRepository.removeAllByUser(user);
    }

    @Override
    public List<ProjectDTO> sortByUserId(@NotNull final String userId, @NotNull final String parameter) {
        StringValidator.validate(userId, parameter);
        final User user = getUser(userId);
        if (user == null) return null;
        switch (parameter) {
            case "order":
                return findAllByUserId(userId);
            case "status":
                return projectRepository.findAllByUserOrderByStatus(user)
                        .stream().map(Project::getProjectDTO).collect(Collectors.toList());
            case "dateStart":
                return projectRepository.findAllByUserOrderByDateStart(user)
                        .stream().map(Project::getProjectDTO).collect(Collectors.toList());
            case "dateEnd":
                return projectRepository.findAllByUserOrderByDateEnd(user)
                        .stream().map(Project::getProjectDTO).collect(Collectors.toList());
            default:
                throw new IllegalArgumentException("WRONG PARAMENTER");
        }
    }

    @Override
    public List<ProjectDTO> findByPartOfNameOrDescription(@NotNull final String userId, @NotNull final String keyWord) {
        StringValidator.validate(userId, keyWord);
        final User user = getUser(userId);
        if (user == null) return null;
        return projectRepository.findAllByPartOfNameOrDescription(user, keyWord).stream()
                .map(Project::getProjectDTO).collect(Collectors.toList());
    }

    @Override
    public ProjectDTO findOneByUserId(@NotNull final String userId, @NotNull final String id) {
        final User user = getUser(userId);
        if (user == null) return null;
        @Nullable final Project project = projectRepository.findProjectByUserAndId(user, id).orElse(null);
        if (project == null) return null;
        return project.getProjectDTO();
    }

    @Override
    public void persist(@NotNull final ProjectDTO projectDTO) {
        @NotNull final Project project = getProjectFromDTO(projectDTO);
        projectRepository.save(project);
    }

    @Override
    public void merge(@NotNull final ProjectDTO projectDTO) {
        @NotNull final Project project = getProjectFromDTO(projectDTO);
        projectRepository.save(project);
    }

    @Override
    public void removeById(@NotNull String id) {
        projectRepository.deleteById(id);
    }

    @Override
    public ProjectDTO findOne(@NotNull final String id) {
        StringValidator.validate(id);
        @Nullable final Project project = projectRepository.findById(id).orElse(null);
        if (project == null) return null;
        return project.getProjectDTO();
    }

    @Override
    public List<ProjectDTO> findAll() {
        return projectRepository.findAll().stream().map(Project::getProjectDTO).collect(Collectors.toList());
    }

    private User getUser(@NotNull final String userId) {
        return userRepository.findById(userId).orElse(null);
    }

    private Project getProjectFromDTO(@NotNull final ProjectDTO projectDTO) {
        final Project project = new Project();
        project.setId(projectDTO.getId());
        project.setUser(getUser(projectDTO.getUserId()));
        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        project.setDateStart(projectDTO.getDateStart());
        project.setDateEnd(projectDTO.getDateEnd());
        project.setStatus(projectDTO.getStatus());
        return project;
    }

}
