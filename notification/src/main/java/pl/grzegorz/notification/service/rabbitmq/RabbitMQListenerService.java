package pl.grzegorz.notification.service.rabbitmq;

import pl.grzegorz.notification.model.NotificationInfoDto;

public interface RabbitMQListenerService {

    void handlerFinishEnroll(NotificationInfoDto notification);
}
