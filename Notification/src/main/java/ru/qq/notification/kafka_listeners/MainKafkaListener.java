package ru.qq.notification.kafka_listeners;

import org.springframework.kafka.support.Acknowledgment;
import ru.qq.common.payload.NotificationPayload;

public interface MainKafkaListener {
    void consumeNotification(NotificationPayload notificationPayload, Acknowledgment acknowledgment);
}
