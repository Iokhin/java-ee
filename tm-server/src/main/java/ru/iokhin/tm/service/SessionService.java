package ru.iokhin.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;
import ru.iokhin.tm.api.service.ISessionService;
import ru.iokhin.tm.exception.AuthException;
import ru.iokhin.tm.model.dto.UserDTO;

import javax.faces.context.FacesContext;
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

    @Override
    public boolean isUser(@Nullable final HttpSession session) {
        @NotNull final UserDTO loggedUser = (UserDTO) session.getAttribute("user");
        if (loggedUser == null) return false;
        return true;
    }

    @Override
    public UserDTO getAuthedUser() {
        @NotNull final FacesContext context = FacesContext.getCurrentInstance();
        @NotNull final HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        @NotNull final UserDTO userDTO = (UserDTO) session.getAttribute("user");
        return userDTO;
    }
}
