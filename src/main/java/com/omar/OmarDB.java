package com.omar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@SpringBootApplication
@EnableAsync
@EnableCaching
@EnableWebSecurity
public class OmarDB {
    public static void main(String[] args) {
        SpringApplication.run(OmarDB.class, args);
    }
}
