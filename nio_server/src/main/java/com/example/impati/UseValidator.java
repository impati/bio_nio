package com.example.impati;

import com.example.impati.model.Coupon;
import com.example.impati.model.property.Properties;
import com.example.impati.model.rule.Rule;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UseValidator {

    private final List<Rule> rules;

    public List<Coupon> validate(Properties properties, List<Coupon> coupons) {
        List<Coupon> validCoupons = new ArrayList<>();

        for (Coupon coupon : coupons) {

            try {
                for (Rule rule : rules) {
                    rule.validate(properties, coupon);
                }
            } catch (RuntimeException e) {
                continue;
            }

            validCoupons.add(coupon);
        }

        return validCoupons;
    }
}
