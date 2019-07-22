package ru.iokhin.tm.boot.api.service;

import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.boot.exception.AuthException;
import ru.iokhin.tm.boot.model.dto.UserDTO;

import javax.servlet.http.HttpSession;

public interface ISessionService {

    void validateSession(@Nullable final HttpSession session) throws AuthException;

//    void validateAdminSession(@Nullable final HttpSession session) throws AuthException;

    boolean isUser(@Nullable final HttpSession session);

    UserDTO getAuthedUser();
}
