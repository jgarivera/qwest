package com.jgarivera.qwest.shared.infrastructure;

import com.jgarivera.qwest.shared.application.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
class JavaEmailService implements EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    /**
     * The default sender used in sending email. This is only used for testing and set via reflection.
     * Having a null sender in GreenMail causes a non-deterministic failure relating to jakarta.mail.MessagingException.
     */
    private String sender;

    JavaEmailService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void send(String to, String subject, String template) throws MessagingException {
        send(to, subject, template, new Context());
    }

    @Override
    public void send(String to, String subject, String template, Context context) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        var helper = new MimeMessageHelper(mimeMessage, true);

        if (sender != null) {
            helper.setFrom(sender);
        }

        helper.setTo(to);
        helper.setSubject(subject);

        String htmlContent = templateEngine.process(template, context);
        helper.setText(htmlContent, true);

        mailSender.send(mimeMessage);
    }
}
