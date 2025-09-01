package com.example.impati.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

    private static final String TARGET_SERVER_BASE_URL = "http://ec2-43-200-130-65.ap-northeast-2.compute.amazonaws.com:8080";

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
