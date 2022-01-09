package pl.grzegorz.notification.service.email;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import pl.grzegorz.notification.model.NotificationInfoDto;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailSenderImpl implements EmailSender {

    private final JavaMailSender mailSender;

    public EmailSenderImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmails(NotificationInfoDto notificationInfo) {
        String title = "Zamknięte zapisy na event " + notificationInfo.getEventName();
        StringBuilder text = new StringBuilder();
        text.append("Kurs");
        text.append(notificationInfo.getEventName());
        text.append("rozpoczyna się");
        text.append(notificationInfo.getStartDate());
        text.append("o godzinie");
        text.append(notificationInfo.getStartDate().getHour()).append(":")
                .append(notificationInfo.getStartDate().getMinute());
        text.append("Proszę zabrać ze sobą ciepłe ubrania. Zbiórka przed eventem 30 minut wcześniej");
        text.append("\n");
        text.append("Opis eventu: ").append(notificationInfo.getEventDescription());
        text.append("\n");
        text.append("Event kończy się ").append(notificationInfo.getEndDate().getHour()).append(":")
                .append(notificationInfo.getEndDate().getMinute());
        text.append("Do zobaczenia");
        notificationInfo.getEmails().forEach(email -> {
            try {
                sendEmail(email, title, text.toString());
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    public void sendEmail(String recipient, String title, String content) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setTo(recipient);
        messageHelper.setSubject(title);
        mimeMessage.setText(content);
        mailSender.send(mimeMessage);
    }
}
