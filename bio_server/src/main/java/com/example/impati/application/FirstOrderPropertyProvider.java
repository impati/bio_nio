package com.example.impati.application;

import com.example.impati.model.Coupon;
import com.example.impati.model.Input;
import com.example.impati.model.client.OrderClient;
import com.example.impati.model.property.FirstOrderProperty;
import com.example.impati.model.property_loader.PropertyProvider;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FirstOrderPropertyProvider implements PropertyProvider<FirstOrderProperty> {

    private final OrderClient orderClient;

    @Override
    public FirstOrderProperty provide(final Input input, final List<Coupon> coupons) {
        return new FirstOrderProperty(
                orderClient.hasOrderHistoryB(input.memberNumber())
        );
    }
}
