package com.perfect.bowling.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/bowling/home")
    public String home() {
        return "home";
    }
}
