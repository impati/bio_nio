package com.example.impati.infrastructure;

import com.example.impati.model.Coupon;
import com.example.impati.model.ReactiveCouponRepository;
import java.util.List;
import reactor.core.publisher.Mono;

public class R2dbcCouponRepository implements ReactiveCouponRepository {

    @Override
    public Mono<List<Coupon>> findBy(String memberNumber) {
        return Mono.empty();
    }
}
