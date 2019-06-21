package ru.iokhin.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.service.ISessionService;
import ru.iokhin.tm.enumerated.Role;
import ru.iokhin.tm.exception.AuthException;
import ru.iokhin.tm.model.User;

import javax.servlet.http.HttpSession;

public enum SessionService implements ISessionService {

    INSTANCE;

    @Override
    public void validateSession(@Nullable final HttpSession session) throws AuthException {
        if (session.getAttribute("user") == null)
            throw new AuthException("Session is invalid: \nDoes not found logged user! \nPlease re-login!");
    }

    @Override
    public void validateAdminSession(@Nullable final HttpSession session) throws AuthException {
        validateSession(session);
        @NotNull final User user = (User) session.getAttribute("user");
        if (!user.getRole().equals(Role.ADMIN))
            throw new AuthException("Forbidden action for your role!");

    }
}
