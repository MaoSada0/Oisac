package ru.qq.node.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import ru.qq.common.payload.NotificationPayload;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    private String BOOSTRAP_SERVERS;

    @Value("${spring.kafka.topic.notification}")
    private String TOPIC_NOTIFICATION;

    @Value("${spring.kafka.group-id.from-node}")
    private String GROUP_ID;

    @Bean
    public NewTopic notificationTopic() {
        return TopicBuilder
                .name(TOPIC_NOTIFICATION)
                .build();
    }

    @Bean
    public Map<String, Object> producerConfiguration() {
        Map<String, Object> configProperties = new HashMap<>();
        configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOSTRAP_SERVERS);
        configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        configProperties.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);


        return configProperties;
    }

    @Bean
    public ProducerFactory<String, NotificationPayload> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfiguration());
    }

    @Bean
    public KafkaTemplate<String, NotificationPayload> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

}
