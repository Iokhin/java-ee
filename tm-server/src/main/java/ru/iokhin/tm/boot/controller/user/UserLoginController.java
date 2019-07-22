package ru.iokhin.tm.boot.controller.user;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.iokhin.tm.boot.api.service.IUserService;
import ru.iokhin.tm.boot.exception.AuthException;
import ru.iokhin.tm.boot.model.dto.UserDTO;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@Getter
@Setter
@ApplicationScoped
@Controller
@URLMappings(mappings = {
        @URLMapping(
                id = "login",
                pattern = "/login",
                viewId = "/WEB-INF/view/user/login.xhtml"),
        @URLMapping(
                id = "welcome",
                pattern = "/welcome",
                viewId = "/WEB-INF/view/welcome.xhtml")
})
public class UserLoginController {

    @Autowired
    private IUserService userService;

    private String username;

    private String password;

    private final String message = "WRONG LOGIN OR PASSWORD";

    public String login() {
        UserDTO user = null;
        try {
            user = userService.authUser(username, password);
        } catch (AuthException e) {
            e.printStackTrace();
            saveMessage();
            return null;
        }
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        session.setAttribute("userId", user.getId());
        session.setAttribute("login", user.getLogin());
        session.setAttribute("user", user);
        return "pretty:project-list";
    }

    private void saveMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Warning", "Message: " + message));
    }

    public String logout() {
        return "pretty:logout";
    }

    public String showForm() {
        return "pretty:login";
    }
}
