package pl.grzegorz.notification.service.email;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EmailSenderImplTest {

    private JavaMailSender javaMailSender;

    private EmailSenderImpl emailSender;

    private MimeMessage mimeMessage;

    @Test
    void shouldThrowMessagingExceptionWhenRecipientIsNull() throws MessagingException {
//        given
        MimeMessage mimeMessage = emailSender.
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
//        when

        String recipient = "example@example.com";
        String subject = "Subject";
        String content = "content";
        mimeMessage.setTo(recipient);
        assertEquals(recipient, mimeMessage.getRecipients(MimeMessage.RecipientType.TO)[0].toString());
    }

}