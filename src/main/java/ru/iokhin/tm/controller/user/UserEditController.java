package ru.iokhin.tm.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import ru.iokhin.tm.api.service.IUserService;
import ru.iokhin.tm.model.dto.UserDTO;

public class UserEditController {

    @Autowired
    private IUserService userService;

    private UserDTO userToEdit;

    public String userEdit() {
        return "pretty:user-edit";
    }

    public void userSave() {
        userService.merge(userToEdit);
    }
}
