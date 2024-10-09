package ru.qq.node.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.qq.common.payload.NotificationPayload;
import ru.qq.node.service.KafkaService;

@Service
@RequiredArgsConstructor
@Log4j
public class KafkaServiceImpl implements KafkaService {

    @Value("${spring.kafka.topic.notification}")
    private String TOPIC_NOTIFICATION;

    private final KafkaTemplate<String, NotificationPayload> kafkaTemplate;

    @Override
    public void processNotification(NotificationPayload notificationPayload) {
        kafkaTemplate.send(TOPIC_NOTIFICATION, notificationPayload);
    }
}
