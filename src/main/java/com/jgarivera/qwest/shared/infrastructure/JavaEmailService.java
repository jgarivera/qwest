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

        helper.setTo(to);
        helper.setSubject(subject);

        String htmlContent = templateEngine.process(template, context);
        helper.setText(htmlContent, true);

        mailSender.send(mimeMessage);
    }
}
