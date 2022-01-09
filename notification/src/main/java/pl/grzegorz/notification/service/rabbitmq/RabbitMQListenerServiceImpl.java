package pl.grzegorz.notification.service.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import pl.grzegorz.notification.model.NotificationInfoDto;
import pl.grzegorz.notification.service.email.EmailSender;

@Service
public class RabbitMQListenerServiceImpl implements RabbitMQListenerService {

    private final EmailSender emailSender;

    public RabbitMQListenerServiceImpl(EmailSender emailSender) {
        this.emailSender = emailSender;
    }
    @Override
    @RabbitListener(queues = "enroll_finish_event")
    public void handlerFinishEnroll(NotificationInfoDto notification) {
        emailSender.sendEmails(notification);
    }
}

