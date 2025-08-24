package com.example.impati.model;

import java.util.List;
import reactor.core.publisher.Mono;

public interface ReactiveCouponRepository {

    Mono<List<Coupon>> findBy(String memberNumber);
}
