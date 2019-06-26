package ru.iokhin.tm.servlet.user;

import org.jetbrains.annotations.NotNull;
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

@WebServlet(urlPatterns = "/login")
public class UserLoginServlet extends HttpServlet {

    @NotNull
    private final IUserService userService = UserService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/user/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        @NotNull final String login = req.getParameter("login");
        @NotNull final String password = req.getParameter("password");
        StringValidator.validate(login, password);
        try {
            User user =  userService.authUser(login, password);
            HttpSession session = req.getSession(true);
            session.setAttribute("userId", user.getId());
            session.setAttribute("userLogin", user.getLogin());
            resp.sendRedirect("/project-list");
        } catch (AuthException e) {
            resp.sendError(401, e.getMessage());
        }
    }
}
