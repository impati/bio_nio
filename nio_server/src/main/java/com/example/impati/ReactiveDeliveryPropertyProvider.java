package com.example.impati;

import com.example.impati.model.Coupon;
import com.example.impati.model.Input;
import com.example.impati.model.client.ReactiveOrderClient;
import com.example.impati.model.property.DeliveryProperty;
import com.example.impati.model.property_loader.ReactivePropertyProvider;
import java.util.List;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ReactiveDeliveryPropertyProvider implements ReactivePropertyProvider<DeliveryProperty> {

    private final ReactiveOrderClient orderClient;

    @Override
    public Mono<DeliveryProperty> provide(final Input input, final List<Coupon> coupons) {
        return orderClient.getOrderDetail(input.orderNumber())
                          .map(it -> new DeliveryProperty(it.deliveryType()));
    }
}
