package com.jgarivera.qwest.identity.presentation;

import com.jgarivera.qwest.identity.application.UserService;
import com.jgarivera.qwest.identity.domain.model.EmailAddress;
import com.jgarivera.qwest.identity.domain.model.PersonalName;
import com.jgarivera.qwest.identity.domain.model.Username;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
class RegistrationPageController {

    private final UserService userService;

    RegistrationPageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    String register(@ModelAttribute("form") RegistrationForm form) {
        return "pages/register";
    }

    @PostMapping("/register")
    String register(@Valid @ModelAttribute("form") RegistrationForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "pages/register";
        }

        userService.register(
                new PersonalName(form.getFirstName(), form.getMiddleName(), form.getLastName()),
                new EmailAddress(form.getEmail()),
                new Username(form.getUsername()),
                form.getPassword()
        );

        return "redirect:/login?registered";
    }
}
