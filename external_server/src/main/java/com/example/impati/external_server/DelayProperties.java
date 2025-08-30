package com.example.impati.external_server;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "delay")
public record DelayProperties(
        long shopFranchise,
        long shopCategories,
        long membership,
        long hasOrderHistory,
        long getOrder
) {

}
