package ru.iokhin.tm.controller.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.iokhin.tm.api.service.IUserService;
import ru.iokhin.tm.model.User;
import ru.iokhin.tm.util.MD5Util;
import ru.iokhin.tm.util.StringValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class UserEditController {

    @NotNull
    @Autowired
    private IUserService userService;

    @GetMapping("/user-edit")
    public String showForm(@NotNull final HttpSession session, @NotNull Model model) throws Exception {
        @NotNull final String userId = session.getAttribute("userId").toString();
        @Nullable final User user = userService.findOne(userId);
        if (user == null) throw new Exception("USER NOT FOUND");
        model.addAttribute("user", user);
        model.addAttribute("userId", userId);
        return "/user/user-edit";
    }

    @PostMapping("/user-edit")
    public String editUser(@RequestParam final String userId,
                            @RequestParam final String login,
                            @RequestParam final String password) throws Exception {
        final User user = userService.findOne(userId);
        if (user == null) throw new Exception("NO SUCH USER");
        StringValidator.validate(login, password);
        user.setLogin(login);
        user.setPasswordHash(MD5Util.passwordToHash(password));
        userService.merge(user);
        return "redirect:/project-list";
    }
}
