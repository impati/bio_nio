package com.example.impati.model.client;

import java.util.List;
import reactor.core.publisher.Mono;

public interface ReactiveExternalCouponClient {

    Mono<List<Long>> issuable(String memberNumber, List<Long> couponIds);

}
