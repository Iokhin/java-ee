package ru.iokhin.tm.boot.endpoint.rest;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.iokhin.tm.boot.api.service.ITaskService;
import ru.iokhin.tm.boot.model.dto.ResultDTO;
import ru.iokhin.tm.boot.model.dto.TaskDTO;

import java.util.List;

@RestController
@RequestMapping("api/task")
public class TaskEndpointREST {

    @Autowired
    private ITaskService taskService;

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDTO create(@RequestParam(name = "userId") @NotNull String userId) {
        taskService.create(userId);
        return new ResultDTO(true);
    }

    @GetMapping(value = "/findAllByUserId", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<TaskDTO> findAllByUserId(@RequestParam(name = "userId") @NotNull String userId) {
        return taskService.findAllByUserId(userId);
    }

    @PostMapping(value = "/removeAllByUserId", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDTO removeAllByUserId(@RequestParam(name = "userId") @NotNull String userId) {
        taskService.removeAllByUserId(userId);
        return new ResultDTO(true);
    }

    @GetMapping(value = "/sortByUserId", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<TaskDTO> sortByUserId(@RequestParam(name = "userId") @NotNull String userId, @RequestParam(name = "parametr") @NotNull String parametr) {
        return taskService.sortByUserId(userId, parametr);
    }

    @GetMapping(value = "/findByPartOfNameOrDescription", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<TaskDTO> findByPartOfNameOrDescription(@RequestParam(name = "userId") @NotNull String userId, @RequestParam(name = "keyWord") @NotNull String keyWord) {
        return taskService.findByPartOfNameOrDescription(userId, keyWord);
    }

    @GetMapping(value = "/findAllByProjectId", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<TaskDTO> findAllByProjectId(@RequestParam(name = "userId") @NotNull String userId, @RequestParam(name = "projectId") @NotNull String projectId) {
        return taskService.findAllByProjectId(userId, projectId);
    }

    @GetMapping(value = "/findOneByUserId", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public TaskDTO findOneByUserId(@RequestParam(name = "userId") @NotNull String userId, @RequestParam(name = "id") @NotNull String id) {
        return taskService.findOneByUserId(userId, id);
    }

    @PostMapping(value = "/persist", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDTO persist(@RequestBody @NotNull TaskDTO entity) {
        taskService.persist(entity);
        return new ResultDTO(true);
    }

    @PostMapping(value = "/merge", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDTO merge(@RequestBody @NotNull TaskDTO entity) {
        taskService.merge(entity);
        return new ResultDTO(true);
    }

    @PostMapping(value = "/removeById", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDTO removeById(@RequestParam(name = "id") @NotNull String id) {
        taskService.removeById(id);
        return new ResultDTO(true);
    }

    @GetMapping(value = "/findOne", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public TaskDTO findOne(@RequestParam(name = "id") @NotNull String id) {
        return taskService.findOne(id);
    }

    @GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<TaskDTO> findAll() {
        return taskService.findAll();
    }

}
