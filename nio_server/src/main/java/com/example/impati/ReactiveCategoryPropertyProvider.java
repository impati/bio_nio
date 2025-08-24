package com.example.impati;

import com.example.impati.model.Coupon;
import com.example.impati.model.Input;
import com.example.impati.model.client.ReactiveShopClient;
import com.example.impati.model.client.ShopClient;
import com.example.impati.model.property.CategoryProperty;
import com.example.impati.model.property_loader.PropertyProvider;
import com.example.impati.model.property_loader.ReactivePropertyProvider;
import java.util.List;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ReactiveCategoryPropertyProvider implements ReactivePropertyProvider<CategoryProperty> {

    private final ReactiveShopClient reactiveShopClient;

    @Override
    public Mono<CategoryProperty> provide(final Input input, final List<Coupon> coupons) {

        return reactiveShopClient.categories(input.shopNumber())
                .map(CategoryProperty::new);

    }
}
