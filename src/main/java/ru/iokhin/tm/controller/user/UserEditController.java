package ru.iokhin.tm.controller.user;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.iokhin.tm.api.service.IUserService;
import ru.iokhin.tm.model.dto.UserDTO;
import ru.iokhin.tm.util.MD5Util;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@Getter
@Setter
@ApplicationScoped
@Controller
@URLMapping(
        id = "user-edit",
        pattern = "/user-edit",
        viewId = "/WEB-INF/view/user/user-edit.xhtml"
)
public class UserEditController {

    @Autowired
    private IUserService userService;

    private UserDTO userToEdit;

    public String userEdit() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        userToEdit = (UserDTO) session.getAttribute("user");
        return "pretty:user-edit";
    }

    public void userSave() {
        userToEdit.setPasswordHash(MD5Util.passwordToHash(userToEdit.getPasswordHash()));
        userService.merge(userToEdit);
    }
}
