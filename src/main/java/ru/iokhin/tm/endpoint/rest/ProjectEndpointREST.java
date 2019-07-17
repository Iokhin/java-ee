package ru.iokhin.tm.endpoint.rest;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.iokhin.tm.api.service.IProjectService;
import ru.iokhin.tm.model.dto.ProjectDTO;
import ru.iokhin.tm.model.dto.ResultDTO;

import java.util.List;

@RestController
@RequestMapping(value = "/api/project")
public class ProjectEndpointREST {

    @Autowired
    private IProjectService projectService;

    @GetMapping(value = "/findAllByUserId", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ProjectDTO> findAllByUserId(@RequestParam(name = "userId") @NotNull String userId) {
        return projectService.findAllByUserId(userId);
    }

    @PostMapping(value = "/removeAllByUserId", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDTO removeAllByUserId(@RequestParam(name = "userId") @NotNull String userId) {
        projectService.removeAllByUserId(userId);
        return new ResultDTO(true);
    }

    @GetMapping(value = "/sortByUserId", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ProjectDTO> sortByUserId(@RequestParam(name = "userId") @NotNull String userId, @RequestParam(name = "parameter") @NotNull String parameter) {
        return projectService.sortByUserId(userId, parameter);
    }

    @GetMapping(value = "/findByPartOfNameOrDescription", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ProjectDTO> findByPartOfNameOrDescription(@RequestParam(name = "userId") @NotNull String userId, @RequestParam(name = "keyWord") @NotNull String keyWord) {
        return projectService.findByPartOfNameOrDescription(userId, keyWord);
    }

    @GetMapping(value = "/findOneByUserId", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ProjectDTO findOneByUserId(@RequestParam(name = "userId") @NotNull String userId, @RequestParam(name = "id") @NotNull String id) {
        return projectService.findOneByUserId(userId, id);
    }

    @PostMapping(value = "/persist", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDTO persist(@RequestParam(name = "entity") @NotNull ProjectDTO entity) {
        projectService.persist(entity);
        return new ResultDTO(true);
    }

    @PostMapping(value = "/merge", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDTO merge(@RequestParam(name = "entity") @NotNull ProjectDTO entity) {
        projectService.merge(entity);
        return new ResultDTO(true);
    }

    @PostMapping(value = "/removeById", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDTO removeById(@RequestParam(name = "id") @NotNull String id) {
        projectService.removeById(id);

        return new ResultDTO(true);
    }

    @GetMapping(value = "/findOne", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ProjectDTO findOne(@RequestParam(name = "id") @NotNull String id) {
        return projectService.findOne(id);
    }

    @GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ProjectDTO> findAll() {
        return projectService.findAll();
    }
}
