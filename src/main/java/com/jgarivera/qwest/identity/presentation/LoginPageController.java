package com.jgarivera.qwest.identity.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
class LoginPageController {

    @GetMapping("/login")
    private String login() {
        return "pages/login";
    }

    @GetMapping("/login-error")
    private String loginError(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", true);
        return "redirect:/login";
    }
}
