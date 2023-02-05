package com.omar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@SpringBootApplication
@EnableAsync
@EnableCaching
@EnableWebSecurity
public class OmarDB {
    @Value("${nodeType}")
    private String nodeType;

    @Value("${nodeId}")
    private String nodeId;
    public static void main(String[] args) {
        SpringApplication.run(OmarDB.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate) {
//        return args -> {
////            for (int i = 1; i <= 10; ++i) {
////                kafkaTemplate.send("CREATE", "HELLO #" + i + " from " + nodeType + " " + nodeId);
////            }
//        };
//    }
}
