package ru.iokhin.tm.boot.endpoint.soap;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import ru.iokhin.tm.boot.api.endpoint.IProjectEndpoint;
import ru.iokhin.tm.boot.api.service.IProjectService;
import ru.iokhin.tm.boot.model.dto.ProjectDTO;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "ru.iokhin.tm.boot.api.endpoint.IProjectEndpoint")
public class ProjectEndpoint implements IProjectEndpoint {

    @Autowired
    private IProjectService projectService;


    @Override
    public List<ProjectDTO> findAllByUserId(@NotNull String userId) {
        return projectService.findAllByUserId(userId);
    }

    @Override
    public void removeAllByUserId(@NotNull String userId) {
        projectService.removeAllByUserId(userId);
    }

    @Override
    public List<ProjectDTO> sortByUserId(@NotNull String userId, @NotNull String parameter) {
        return projectService.sortByUserId(userId, parameter);
    }

    @Override
    public List<ProjectDTO> findByPartOfNameOrDescription(@NotNull String userId, @NotNull String keyWord) {
        return projectService.findByPartOfNameOrDescription(userId, keyWord);
    }

    @Override
    public ProjectDTO findOneByUserId(@NotNull String userId, @NotNull String id) {
        return projectService.findOneByUserId(userId, id);
    }

    @Override
    public void persist(@NotNull ProjectDTO entity) {
        projectService.persist(entity);
    }

    @Override
    public void merge(@NotNull ProjectDTO entity) {
        projectService.merge(entity);
    }

    @Override
    public void removeById(@NotNull String id) {
        projectService.removeById(id);
    }

    @Override
    public ProjectDTO findOne(@NotNull String id) {
        return projectService.findOne(id);
    }

    @Override
    public List<ProjectDTO> findAll() {
        return projectService.findAll();
    }
}
