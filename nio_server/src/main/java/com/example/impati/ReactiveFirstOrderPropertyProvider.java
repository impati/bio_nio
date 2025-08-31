package com.example.impati;

import com.example.impati.model.Coupon;
import com.example.impati.model.Input;
import com.example.impati.model.client.ReactiveOrderClient;
import com.example.impati.model.property.FirstOrderProperty;
import com.example.impati.model.property_loader.ReactivePropertyProvider;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ReactiveFirstOrderPropertyProvider implements ReactivePropertyProvider<FirstOrderProperty> {

    private final ReactiveOrderClient orderClient;

    @Override
    public Mono<FirstOrderProperty> provide(final Input input, final List<Coupon> coupons) {
        return orderClient.hasOrderHistory(input.memberNumber())
                          .map(FirstOrderProperty::new);
    }
}
