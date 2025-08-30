package com.example.impati.model;

import java.util.List;
import reactor.core.publisher.Mono;

public interface ReactiveShopCouponClient {

    Mono<List<ShopCoupon>> getShopCoupon(String shopNumber);
}
