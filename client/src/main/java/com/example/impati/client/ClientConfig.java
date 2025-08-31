package com.example.impati.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

    private static final String TARGET_SERVER_BASE_URL = "http://localhost:8080";

    @Bean
    public ExternalCouponProperty externalCouponProperty() {
        return new ExternalCouponProperty(
                TARGET_SERVER_BASE_URL
        );
    }

    @Bean
    public MemberClientProperty memberClientProperty() {
        return new MemberClientProperty(
                TARGET_SERVER_BASE_URL
        );
    }

    @Bean
    public ShopClientProperty shopClientProperty() {
        return new ShopClientProperty(
                TARGET_SERVER_BASE_URL
        );
    }

    @Bean
    public OrderClientProperty orderClientProperty() {
        return new OrderClientProperty(
                TARGET_SERVER_BASE_URL
        );
    }
}
