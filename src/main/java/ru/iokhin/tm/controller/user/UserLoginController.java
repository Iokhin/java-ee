package ru.iokhin.tm.controller.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.iokhin.tm.api.service.IUserService;
import ru.iokhin.tm.exception.AuthException;
import ru.iokhin.tm.model.User;
import ru.iokhin.tm.service.UserService;
import ru.iokhin.tm.util.StringValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class UserLoginController {

    @NotNull
    @Autowired
    private IUserService userService;

    @GetMapping("/login")
    public String showForm() {
        return "/user/login";
    }

    @PostMapping("/login")
    public String userLogin(@RequestParam("login") @Nullable final String login,
                           @RequestParam("password") @Nullable final String password,
                           @NotNull final HttpSession session) {
        try {
            StringValidator.validate(login, password);
            User user = userService.authUser(login, password);
            session.setAttribute("userId", user.getId());
            session.setAttribute("userLogin", user.getLogin());
            return "redirect:/project-list";
        } catch (AuthException e) {
            return "redirect:/login";
        }
    }
}
