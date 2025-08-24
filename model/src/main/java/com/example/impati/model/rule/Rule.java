package com.example.impati.model.rule;

import com.example.impati.model.Coupon;
import com.example.impati.model.property.Properties;
import com.example.impati.model.property.Property;
import java.util.List;

public interface Rule {

    void validate(Properties properties, Coupon coupon);

    List<Coupon> filter(Property property, List<Coupon> coupons);
}
