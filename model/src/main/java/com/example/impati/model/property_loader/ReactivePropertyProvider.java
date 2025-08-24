package com.example.impati.model.property_loader;

import com.example.impati.model.Coupon;
import com.example.impati.model.Input;
import java.util.List;
import reactor.core.publisher.Mono;

public interface ReactivePropertyProvider<P> {

    Mono<P> provide(Input input, List<Coupon> coupons);
}
