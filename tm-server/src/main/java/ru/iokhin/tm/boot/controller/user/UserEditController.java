package ru.iokhin.tm.boot.controller.user;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import ru.iokhin.tm.boot.api.service.ISecurityService;
import ru.iokhin.tm.boot.api.service.IUserService;
import ru.iokhin.tm.boot.model.dto.UserDTO;

import javax.faces.bean.ApplicationScoped;

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
    private ISecurityService securityService;

    @Autowired
    private IUserService userService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    private UserDTO userToEdit;

    public String userEdit() {
        userToEdit = securityService.getLoggedUser();
        return "pretty:user-edit";
    }

    @PreAuthorize("hasRole('USER')")
    public void userSave() {
        userToEdit.setPassword(encoder.encode(userToEdit.getPassword()));
        userService.merge(userToEdit);
    }
}
