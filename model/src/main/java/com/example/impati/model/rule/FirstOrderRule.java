package com.example.impati.model.rule;

import com.example.impati.model.Coupon;
import com.example.impati.model.property.FirstOrderProperty;
import com.example.impati.model.property.Properties;
import com.example.impati.model.property.Property;
import java.util.List;

public class FirstOrderRule implements Rule {

    @Override
    public void validate(final Properties properties, final Coupon coupon) {
        if (!coupon.onlyFirstOrder()) {
            return;
        }
        FirstOrderProperty property = properties.getOrThrow(FirstOrderProperty.class);

        if (!property.firstOrder()) {
            throw new IllegalArgumentException("첫주문 유저가 아닙니다.");
        }
    }

    @Override
    public List<Coupon> filter(final Property property, final List<Coupon> coupons) {
        return coupons;
    }
}
