package com.example.impati.external_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(DelayProperties.class)
public class ExternalServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExternalServerApplication.class, args);
    }
}
