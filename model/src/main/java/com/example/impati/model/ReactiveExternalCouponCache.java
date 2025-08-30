package com.example.impati.model;

import java.util.List;
import java.util.Optional;
import reactor.core.publisher.Mono;

public interface ReactiveExternalCouponCache {

    Mono<Void> put(String memberNumber, List<Long> couponIds);

    Mono<Optional<List<Long>>> get(String memberNumber, List<Long> couponIds);
}
