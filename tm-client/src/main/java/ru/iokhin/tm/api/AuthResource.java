package ru.iokhin.tm.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.iokhin.tm.model.ResultDTO;
import ru.iokhin.tm.model.UserDTO;

public interface AuthResource {
    @GetMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    ResultDTO login(@RequestParam("username") String username, @RequestParam("password") String password);

    @GetMapping(value = "/profile", produces = MediaType.APPLICATION_JSON_VALUE)
    UserDTO profile();

    @GetMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    ResultDTO logout();
}
