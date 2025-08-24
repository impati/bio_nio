package com.example.impati.model.rule;

import com.example.impati.model.Coupon;
import com.example.impati.model.property.FranchiseProperty;
import com.example.impati.model.property.Properties;
import com.example.impati.model.property.Property;
import java.util.List;

public class FranchiseRule implements Rule {

    @Override
    public void validate(final Properties properties, final Coupon coupon) {
        if (coupon.isFranchiseCoupon()) {
            return;
        }
        FranchiseProperty franchiseProperty = properties.getOrThrow(FranchiseProperty.class);

        if (!coupon.containFranchise(franchiseProperty.franchiseNumber())) {
            throw new IllegalArgumentException("프렌차이즈 정보가 일치하지 않습니다.");
        }
    }

    @Override
    public List<Coupon> filter(final Property property, final List<Coupon> coupons) {
        return coupons;
    }
}
