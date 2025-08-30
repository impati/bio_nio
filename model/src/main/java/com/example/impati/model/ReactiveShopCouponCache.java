package com.example.impati.model;

import java.util.List;
import java.util.Optional;
import reactor.core.publisher.Mono;

public interface ReactiveShopCouponCache {

    Mono<Optional<List<ShopCoupon>>> getShopCoupon(String shopNumber);

    Mono<Void> put(String shopNumber, List<ShopCoupon> shopCoupons);

}
