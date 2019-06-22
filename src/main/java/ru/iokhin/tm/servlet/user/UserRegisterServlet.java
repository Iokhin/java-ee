package ru.iokhin.tm.servlet.user;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.service.IUserService;
import ru.iokhin.tm.enumerated.Role;
import ru.iokhin.tm.model.User;
import ru.iokhin.tm.service.UserService;
import ru.iokhin.tm.util.StringValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/register")
public class UserRegisterServlet extends HttpServlet {

    @NotNull
    private final IUserService userService = UserService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        @NotNull final String login = req.getParameter("login");
        @NotNull final String password = req.getParameter("password");
        StringValidator.validate(login, password);
        @NotNull final User user = new User(login, password, Role.USER);
        userService.persist(user);
        req.getSession().setAttribute("userId", user.getId());
        req.getSession().setAttribute("userLogin", user.getLogin());
        resp.sendRedirect("/welcome");
    }
}
