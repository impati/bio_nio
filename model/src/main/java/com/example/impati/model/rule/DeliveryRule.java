package com.example.impati.model.rule;

import com.example.impati.model.Coupon;
import com.example.impati.model.property.DeliveryProperty;
import com.example.impati.model.property.Properties;
import com.example.impati.model.property.Property;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class DeliveryRule implements Rule {

    @Override
    public void validate(final Properties properties, final Coupon coupon) {
        DeliveryProperty property = properties.getOrThrow(DeliveryProperty.class);
        if (!coupon.deliveryType().equals(property.type())) {
            throw new IllegalArgumentException("사용 가능한 배달 유형이 아닙니다");
        }
    }

    @Override
    public List<Coupon> filter(final Property property, final List<Coupon> coupons) {
        return coupons;
    }
}
