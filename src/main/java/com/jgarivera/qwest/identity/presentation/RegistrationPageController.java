package com.jgarivera.qwest.identity.presentation;

import com.jgarivera.qwest.identity.application.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
class RegistrationPageController {

    private final UserService userService;

    RegistrationPageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    private String register(@ModelAttribute("form") RegistrationForm form) {
        return "pages/register";
    }

    @PostMapping("/register")
    private String register(@Valid @ModelAttribute("form") RegistrationForm form,
                    BindingResult result,
                    RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "pages/register";
        }

        userService.register(
                form.getFirstName(),
                form.getMiddleName(),
                form.getLastName(),
                form.getEmail(),
                form.getUsername(),
                form.getPassword()
        );

        redirectAttributes.addFlashAttribute("registered", true);

        return "redirect:/login";
    }
}
