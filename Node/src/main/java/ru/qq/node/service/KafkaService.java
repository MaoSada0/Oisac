package ru.qq.node.service;

import ru.qq.common.payload.NotificationPayload;

public interface KafkaService {
    void processNotification(NotificationPayload notificationPayload);
}
