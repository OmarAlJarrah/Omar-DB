package com.omar.config.kafka;

import com.omar.constant.KafkaTopics;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicsConfig {
  @Bean
  public NewTopic writeTopic() {
    return TopicBuilder.name(KafkaTopics.UPDATE.topic)
        .build();
  }

  @Bean
  public NewTopic deleteTopic() {
    return TopicBuilder.name(KafkaTopics.DELETE.topic)
        .build();
  }

  @Bean
  public NewTopic createTopic() {
    return TopicBuilder.name(KafkaTopics.CREATE.topic)
        .build();
  }
}
