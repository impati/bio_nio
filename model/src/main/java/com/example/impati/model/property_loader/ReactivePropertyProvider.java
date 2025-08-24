package com.example.impati.model.property_loader;

import com.example.impati.model.Coupon;
import com.example.impati.model.Input;
import com.example.impati.model.property.Property;
import java.util.List;
import reactor.core.publisher.Mono;

public interface ReactivePropertyProvider<P extends Property> {

    Mono<P> provide(Input input, List<Coupon> coupons);
}
