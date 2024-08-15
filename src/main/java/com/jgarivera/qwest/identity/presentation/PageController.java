package com.jgarivera.qwest.identity.presentation;

import com.jgarivera.qwest.identity.domain.User;
import com.jgarivera.qwest.identity.domain.UserRepository;
import com.jgarivera.qwest.shared.UUIDFactory;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("auth")
class PageController {

    private final UserRepository repository;
    private final UUIDFactory uuidFactory;
    private final PasswordEncoder passwordEncoder;

    PageController(UserRepository repository, UUIDFactory uuidFactory, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.uuidFactory = uuidFactory;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    String login() {
        return "pages/login";
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

        User user = form.toUser(uuidFactory, passwordEncoder);
        repository.save(user);

        return "redirect:/login?registered";
    }
}
