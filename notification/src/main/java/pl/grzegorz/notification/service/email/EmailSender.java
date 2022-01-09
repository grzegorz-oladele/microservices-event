package pl.grzegorz.notification.service.email;

import pl.grzegorz.notification.model.NotificationInfoDto;

import javax.mail.MessagingException;

public interface EmailSender {

    void sendEmails(NotificationInfoDto notificationInfo);

    void sendEmail(String recipient, String title, String content) throws MessagingException;
}
