package ru.iokhin.tm.servlet.user;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.service.IUserService;
import ru.iokhin.tm.model.User;
import ru.iokhin.tm.service.UserService;
import ru.iokhin.tm.util.MD5Util;
import ru.iokhin.tm.util.StringValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user-edit")
public class UserEditServlet extends HttpServlet {

    @NotNull
    private final IUserService userService = UserService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/user/user-edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String userId = req.getSession().getAttribute("userId").toString();
        final User user = userService.findOne(userId);
        if (user == null) resp.sendError(401, "NO SUCH USER");
        final String newLogin = req.getParameter("login");
        final  String newPassword = req.getParameter("password");
        StringValidator.validate(newLogin, newPassword);
        user.setLogin(newLogin);
        user.setPasswordHash(MD5Util.passwordToHash(newPassword));
        userService.merge(user);
        resp.sendRedirect("/project-list");
    }
}
