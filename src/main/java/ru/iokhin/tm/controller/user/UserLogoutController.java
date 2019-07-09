//package ru.iokhin.tm.controller.user;
//
//import org.jetbrains.annotations.NotNull;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import javax.servlet.http.HttpSession;
//
//@Controller
//public class UserLogoutController {
//    @GetMapping("/logout")
//    protected String logout(@NotNull final HttpSession session) {
//        session.setAttribute("userId", null);
//        session.setAttribute("userLogin", null);
//        return "redirect:/login";
//    }
//}
