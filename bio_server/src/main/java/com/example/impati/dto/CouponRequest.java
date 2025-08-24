package com.example.impati.dto;

import java.util.List;

public record CouponRequest(
        List<String> franchiseNumbers,
        List<String> categories,
        String deliveryType,
        boolean firstOrder,
        boolean membership
) {

}
