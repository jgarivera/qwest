package com.jgarivera.qwest.shared.application;

import jakarta.mail.MessagingException;
import org.thymeleaf.context.Context;

public interface EmailService {

    /**
     * Sends an email with an empty Thymeleaf context.
     *
     * @param to       email recipient
     * @param subject  email subject
     * @param template view name of the Thymeleaf template
     * @throws MessagingException failure in sending email
     */
    void send(String to, String subject, String template) throws MessagingException;

    /**
     * Sends an email with a provided Thymeleaf context.
     *
     * @param to       email recipient
     * @param subject  email subject
     * @param template view name of the Thymeleaf template
     * @param context  context to be used by the Thymeleaf template
     * @throws MessagingException failure in sending email
     */
    void send(String to, String subject, String template, Context context) throws MessagingException;
}
