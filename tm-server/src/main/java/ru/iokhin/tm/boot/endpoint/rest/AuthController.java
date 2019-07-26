package ru.iokhin.tm.boot.endpoint.rest;

import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.iokhin.tm.boot.model.dto.AuthenticationRequestDTO;
import ru.iokhin.tm.boot.model.entity.Role;
import ru.iokhin.tm.boot.repository.UserRepository;
import ru.iokhin.tm.boot.security.jwt.JwtTokenProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    UserRepository userRepository;

    @PostMapping(value = "/auth", produces = "application/json")
//    @CrossOrigin(origins = "http://127.0.0.1:3000")
    public ResponseEntity signin(@RequestBody AuthenticationRequestDTO request) {
        try {
            @Nullable final String username = request.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, request.getPassword()));
            String token = jwtTokenProvider.createToken(username, this.userRepository.findUserByLogin(username).orElseThrow(() ->
                    new UsernameNotFoundException("Username " + username + "not found")).getRoles()
                    .stream().map(Role::getName).collect(Collectors.toList()));
            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
}