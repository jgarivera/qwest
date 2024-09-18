package com.jgarivera.qwest.identity.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class LoginPageController {

    @GetMapping("/login")
    String login() {
        return "pages/login";
    }
}
