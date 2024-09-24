package com.jgarivera.qwest.identity.application;

import com.jgarivera.qwest.identity.domain.model.User;
import com.jgarivera.qwest.identity.domain.model.UserRegistered;
import com.jgarivera.qwest.identity.domain.model.UserRepository;
import com.jgarivera.qwest.shared.application.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.thymeleaf.context.Context;

@Component
class UserRegisteredPolicy {

    private final UserRepository userRepository;
    private final EmailService emailService;

    UserRegisteredPolicy(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    void on(UserRegistered event) {
        User user = userRepository.findById(event.id()).orElseThrow();

        String email = user.getEmail().value();
        String name = user.getName().firstName();

        var context = new Context();
        context.setVariable("name", name);

        try {
            emailService.send(email, "Welcome to qwest!", "email/welcome", context);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
