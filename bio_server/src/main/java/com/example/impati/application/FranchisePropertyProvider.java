package com.example.impati.application;

import com.example.impati.model.Coupon;
import com.example.impati.model.Input;
import com.example.impati.model.client.ShopClient;
import com.example.impati.model.property.FranchiseProperty;
import com.example.impati.model.property_loader.PropertyProvider;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FranchisePropertyProvider implements PropertyProvider<FranchiseProperty> {

    private final ShopClient shopClient;

    @Override
    public FranchiseProperty provide(final Input input, final List<Coupon> coupons) {
        return new FranchiseProperty(
                input.shopNumber(),
                shopClient.getFranchise(input.shopNumber())
        );
    }
}
