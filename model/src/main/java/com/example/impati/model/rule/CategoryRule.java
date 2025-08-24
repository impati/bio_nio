package com.example.impati.model.rule;

import com.example.impati.model.Coupon;
import com.example.impati.model.property.CategoryProperty;
import com.example.impati.model.property.Properties;
import com.example.impati.model.property.Property;
import java.util.List;

public class CategoryRule implements Rule {

    @Override
    public void validate(final Properties properties, final Coupon coupon) {
        if (!coupon.isCategoryCoupon()) {
            return;
        }

        CategoryProperty categoryProperty = properties.getOrThrow(CategoryProperty.class);
        if (!coupon.containCategory(categoryProperty.shopCategories())) {
            throw new IllegalArgumentException("카테고리가 일치하지 않습니다.");
        }
    }

    @Override
    public List<Coupon> filter(final Property property, final List<Coupon> coupons) {
        return null;
    }
}
