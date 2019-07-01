package ru.iokhin.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;
import ru.iokhin.tm.api.service.ISessionService;
import ru.iokhin.tm.exception.AuthException;

import javax.servlet.http.HttpSession;

@Service(SessionService.NAME)
public class SessionService implements ISessionService {

    @NotNull
    public static final String NAME = "sessionService";

    @Override
    public void validateSession(@Nullable final HttpSession session) throws AuthException {
        if (session.getAttribute("userId") == null)
            throw new AuthException("Session is invalid: \nDoes not found logged user! \nPlease re-login!");
    }

}
