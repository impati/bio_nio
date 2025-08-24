package com.example.impati.controller;

import com.example.impati.application.CouponService;
import com.example.impati.dto.CouponRequest;
import com.example.impati.model.Coupon;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CouponApi {

    private final CouponService couponService;

    @PostMapping("/members/{memberNumber}/coupons")
    public Coupon create(@PathVariable String memberNumber, @RequestBody CouponRequest request) {

        return couponService.create(memberNumber, request);
    }
}
