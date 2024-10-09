package ru.qq.notification.kafka_listeners.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import ru.qq.common.payload.NotificationPayload;
import ru.qq.notification.kafka_listeners.MainKafkaListener;


@Component
@RequiredArgsConstructor
@Log4j
public class MainKafkaListenerImpl implements MainKafkaListener {

    @Override
    @KafkaListener(
            topics = "${spring.kafka.topic.notification}",
            groupId = "${spring.kafka.group-id.from-node}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consumeNotification(NotificationPayload notificationPayload, Acknowledgment acknowledgment) {

        try {
            acknowledgment.acknowledge();
            log.debug(notificationPayload.str());
        } catch (Exception e){
            log.error(e.getMessage());
        }

        // TODO: ...
    }
}
