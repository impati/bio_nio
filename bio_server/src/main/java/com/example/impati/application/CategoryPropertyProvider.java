package com.example.impati.application;

import com.example.impati.model.Coupon;
import com.example.impati.model.Input;
import com.example.impati.model.client.ShopClient;
import com.example.impati.model.property.CategoryProperty;
import com.example.impati.model.property_loader.PropertyProvider;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryPropertyProvider implements PropertyProvider<CategoryProperty> {

    private final ShopClient shopClient;

    @Override
    public CategoryProperty provide(final Input input, final List<Coupon> coupons) {

        return new CategoryProperty(shopClient.getCategories(input.shopNumber()));
    }
}
