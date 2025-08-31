package com.example.impati;

import com.example.impati.model.Coupon;
import com.example.impati.model.Input;
import com.example.impati.model.client.ReactiveShopClient;
import com.example.impati.model.property.FranchiseProperty;
import com.example.impati.model.property_loader.ReactivePropertyProvider;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ReactiveFranchisePropertyProvider implements ReactivePropertyProvider<FranchiseProperty> {

    private final ReactiveShopClient shopClient;

    @Override
    public Mono<FranchiseProperty> provide(final Input input, final List<Coupon> coupons) {
        return shopClient.franchise(input.shopNumber())
                         .map(it -> new FranchiseProperty(input.shopNumber(), it));
    }
}
