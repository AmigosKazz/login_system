package com.springsecurity.login_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    // handler methode to handler home page request
    @GetMapping("/index")
    public String home() {
        return "index";
    }
}
