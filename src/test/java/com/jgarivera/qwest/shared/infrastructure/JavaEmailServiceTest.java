package com.jgarivera.qwest.shared.infrastructure;

import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.ServerSetupTest;
import jakarta.mail.Address;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {
        JavaEmailService.class,
        MailSenderAutoConfiguration.class,
        ThymeleafAutoConfiguration.class,
})
class JavaEmailServiceTest {

    @RegisterExtension
    static GreenMailExtension greenMail = new GreenMailExtension(ServerSetupTest.SMTP);

    @Autowired
    JavaEmailService emailService;

    @Test
    void it_sends_email() throws MessagingException {
        ReflectionTestUtils.setField(emailService, "sender", "testsender@email.com");

        emailService.send("test@email.com", "Test subject", "email/welcome");

        MimeMessage[] messages = greenMail.getReceivedMessages();
        assertThat(messages).hasSize(1);

        MimeMessage receivedMessage = messages[0];
        assertThat(receivedMessage.getSubject()).isEqualTo("Test subject");

        Address[] recipients = receivedMessage.getAllRecipients();
        assertThat(recipients[0].toString()).isEqualTo("test@email.com");
    }
}
