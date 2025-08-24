package com.example.impati.application;

import com.example.impati.dto.CouponRequest;
import com.example.impati.model.Coupon;
import com.example.impati.model.CouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;

    public Coupon create(String memberNumber, CouponRequest request) {
        Coupon coupon = new Coupon(
                null,
                memberNumber,
                request.franchiseNumbers(),
                request.categories(),
                request.deliveryType(),
                request.firstOrder(),
                request.membership()
        );

        return couponRepository.save(coupon);
    }
}
