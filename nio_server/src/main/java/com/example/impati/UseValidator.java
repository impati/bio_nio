package com.example.impati;

import com.example.impati.model.Coupon;
import com.example.impati.model.property.Properties;
import com.example.impati.model.rule.Rule;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UseValidator {

    private final List<Rule> rules;

    public List<Coupon> validate(Properties properties, List<Coupon> coupons) {
        List<Coupon> validCoupons = new ArrayList<>();
        log.info("coupons.size(): {}", coupons.size());
        for (Coupon coupon : coupons) {

            try {
                for (Rule rule : rules) {
                    rule.validate(properties, coupon);
                }
            } catch (RuntimeException e) {
                log.info("coupon validate failed, rule: ", e);
                continue;
            }

            validCoupons.add(coupon);
        }

        return validCoupons;
    }
}
