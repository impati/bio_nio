package com.example.impati.model.rule;

import com.example.impati.model.Coupon;
import com.example.impati.model.property.MembershipProperty;
import com.example.impati.model.property.Properties;
import com.example.impati.model.property.Property;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class MemberShipRule implements Rule {

    @Override
    public void validate(final Properties properties, final Coupon coupon) {
        if (!coupon.onlyMembership()) {
            return;
        }

        MembershipProperty property = properties.getOrThrow(MembershipProperty.class);
        if (!property.MemberShip()) {
            throw new IllegalArgumentException("멤버쉽 회원이 아닙니다.");
        }
    }

    @Override
    public List<Coupon> filter(final Property property, final List<Coupon> coupons) {
        return null;
    }
}
