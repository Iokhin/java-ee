package ru.iokhin.tm.servlet.user;

import ru.iokhin.tm.api.service.ISessionService;
import ru.iokhin.tm.exception.AuthException;
import ru.iokhin.tm.service.SessionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/welcome")
public class UserWelcomeServlet extends HttpServlet {

    ISessionService sessionService = SessionService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        try {
//            sessionService.validateSession(req.getSession());
            req.getRequestDispatcher("/WEB-INF/view/user/welcome.jsp").forward(req, resp);
//        } catch (AuthException e) {
//            resp.sendRedirect("/login");
//        }
    }
}
