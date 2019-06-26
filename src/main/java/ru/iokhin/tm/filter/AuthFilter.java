package ru.iokhin.tm.filter;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.service.ISessionService;
import ru.iokhin.tm.api.service.IUserService;
import ru.iokhin.tm.exception.AuthException;
import ru.iokhin.tm.service.SessionService;
import ru.iokhin.tm.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/welcome/*", "/project-create/*", "/project-edit/*", "/project-remove/*", "/project-list/*",
        "/task-create/*", "/task-edit/*", "/task-remove/*", "/task-list/*", "/user-edit/*"})
public class AuthFilter implements Filter {

    @NotNull
    final private IUserService userService = UserService.INSTANCE;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (isAuth(request)) filterChain.doFilter(servletRequest, servletResponse);
        else response.sendRedirect("/login");
    }

    private boolean isAuth(final HttpServletRequest request) {
        if (request == null) return false;
        final HttpSession session = request.getSession();
        if (session == null) return false;
        if (session.getAttribute("userId") == null) return false;
        return true;
    }

    @Override
    public void destroy() {

    }
}
