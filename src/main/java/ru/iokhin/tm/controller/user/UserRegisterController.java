package ru.iokhin.tm.controller.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.iokhin.tm.api.service.IUserService;
import ru.iokhin.tm.enumerated.Role;
import ru.iokhin.tm.model.dto.UserDTO;
import ru.iokhin.tm.model.entity.User;
import ru.iokhin.tm.util.StringValidator;

import javax.servlet.http.HttpSession;

@Controller
public class UserRegisterController {

    @NotNull
    @Autowired
    private IUserService userService;

    @GetMapping("/register")
    public String showForm() {
        return "user/register";
    }

    @PostMapping("/register")
    public String register(@NotNull final HttpSession session, @RequestParam @NotNull final String login,
                           @RequestParam @NotNull final String password,
                           @RequestParam @NotNull final String email) throws Exception {
        StringValidator.validate(login, password);
        @Nullable final UserDTO foundUser = userService.findByLogin(login);
        if (foundUser != null && login.equals(foundUser.getLogin())) {
            throw new Exception("SUCH LOGIN ALREADY EXIST. PLEASE, TRY AGAIN.");
        }
        @NotNull final UserDTO user = new UserDTO(login, password, Role.USER);
        user.setEmail(email);
        userService.persist(user);
        session.setAttribute("userId", user.getId());
        session.setAttribute("userLogin", user.getLogin());
        return "redirect:/project-list";
    }
}
