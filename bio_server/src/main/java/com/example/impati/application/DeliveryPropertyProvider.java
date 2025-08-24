package com.example.impati.application;

import com.example.impati.model.Coupon;
import com.example.impati.model.Input;
import com.example.impati.model.client.OrderClient;
import com.example.impati.model.property.DeliveryProperty;
import com.example.impati.model.property_loader.PropertyProvider;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveryPropertyProvider implements PropertyProvider<DeliveryProperty> {

    private final OrderClient orderClient;

    @Override
    public DeliveryProperty provide(final Input input, final List<Coupon> coupons) {
        return new DeliveryProperty(
                orderClient.getOrderDetailB(input.orderNumber()).deliveryType()
        );
    }
}
