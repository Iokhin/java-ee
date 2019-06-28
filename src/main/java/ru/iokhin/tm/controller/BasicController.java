package ru.iokhin.tm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BasicController {
    @GetMapping("/")
    public String enter() {
        return "redirect:/login";
    }
}
