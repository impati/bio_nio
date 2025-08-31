package com.example.impati.model;

import java.util.List;
import java.util.Optional;
import reactor.core.publisher.Mono;

public interface ReactiveCouponCache {

    Mono<Optional<List<Coupon>>> get(String memberNumber);

    Mono<Void> putCoupon(String memberNumber, List<Coupon> coupons);
}
