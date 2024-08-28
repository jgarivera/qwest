package com.jgarivera.qwest.identity.application;

import com.jgarivera.qwest.identity.domain.User;
import com.jgarivera.qwest.identity.domain.event.UserRegistered;
import com.jgarivera.qwest.shared.application.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.thymeleaf.context.Context;

@Component
class UserRegisteredPolicy {

    private final EmailService emailService;

    UserRegisteredPolicy(EmailService emailService) {
        this.emailService = emailService;
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    void on(UserRegistered event) {
        User user = event.user();
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
