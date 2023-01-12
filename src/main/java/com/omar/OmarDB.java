package com.omar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.UUID;


@SpringBootApplication
@EnableAsync
@EnableCaching
public class OmarDB {
    public static void main(String[] args) {
        SpringApplication.run(OmarDB.class, args);
    }



}
