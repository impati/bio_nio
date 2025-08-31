package com.example.impati;

import com.example.impati.model.Coupon;
import com.example.impati.model.Input;
import com.example.impati.model.property.Properties;
import com.example.impati.model.property_loader.ReactivePropertyProvider;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class DelegatingPropertyProvider {

    private final List<ReactivePropertyProvider<?>> propertyProviders;

    public Mono<Properties> provide(Input input, List<Coupon> coupons) {
        return Flux.fromIterable(propertyProviders)
                   .flatMap(loader -> loader.provide(input, coupons), propertyProviders.size())
                   .collect(Properties::new, Properties::add);
    }
}
