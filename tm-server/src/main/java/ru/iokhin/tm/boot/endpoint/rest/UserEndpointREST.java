package ru.iokhin.tm.boot.endpoint.rest;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.iokhin.tm.boot.api.service.IUserService;
import ru.iokhin.tm.boot.exception.AuthException;
import ru.iokhin.tm.boot.model.dto.ResultDTO;
import ru.iokhin.tm.boot.model.dto.UserDTO;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserEndpointREST {

    @Autowired
    private IUserService userService;

    //THINK ABOUT IT
    public UserDTO authUser(@NotNull String login, @NotNull String password) throws AuthException {
        return null;
    }

    @GetMapping(value = "/findByLogin", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserDTO findByLogin(@RequestParam(name = "login") @NotNull String login) {
        return userService.findByLogin(login);
    }

    @PostMapping(value = "/persist", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDTO persist(@RequestBody @NotNull UserDTO entity) {
        userService.persist(entity);
        return new ResultDTO(true);
    }

    @PostMapping(value = "/merge", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDTO merge(@RequestBody @NotNull UserDTO entity) {
        userService.merge(entity);
        return new ResultDTO(true);
    }

    @PostMapping(value = "/removeById", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDTO removeById(@RequestParam(name = "id") @NotNull String id) {
        userService.removeById(id);
        return new ResultDTO(true);
    }

    @GetMapping(value = "/findOne", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserDTO findOne(@RequestParam(name = "id") @NotNull String id) {
        return userService.findOne(id);
    }

    @GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<UserDTO> findAll() {
        return userService.findAll();
    }
}
