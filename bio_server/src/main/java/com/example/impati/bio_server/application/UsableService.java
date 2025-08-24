package com.example.impati.bio_server.application;

import com.example.impati.model.Coupon;
import com.example.impati.model.CouponRepository;
import com.example.impati.model.Input;
import com.example.impati.model.property.Properties;
import com.example.impati.model.rule.Rule;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsableService {

    private final CouponRepository couponRepository;
    private final DelegatingPropertyProvider delegatingPropertyProvider;
    private final List<Rule> rules;

    public List<Coupon> usable(Input input) {
        List<Coupon> coupons = couponRepository.findBy(input.memberNumber());
        Properties properties = delegatingPropertyProvider.provide(input, coupons);

        List<Coupon> usableCoupon = new ArrayList<>();
        for (Coupon coupon : coupons) {

            try {
                for (Rule rule : rules) {
                    rule.validate(properties, coupon);
                }
            } catch (RuntimeException e) {
                continue;
            }

            usableCoupon.add(coupon);
        }

        return usableCoupon;
    }
}
