package com.example.impati;

import com.example.impati.model.Coupon;
import com.example.impati.model.Input;
import com.example.impati.model.client.ReactiveMemberClient;
import com.example.impati.model.property.MembershipProperty;
import com.example.impati.model.property_loader.ReactivePropertyProvider;
import java.util.List;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ReactiveMembershipPropertyProvider implements ReactivePropertyProvider<MembershipProperty> {

    private final ReactiveMemberClient reactiveMemberClient;

    @Override
    public Mono<MembershipProperty> provide(final Input input, final List<Coupon> coupons) {

        return reactiveMemberClient.isMemberShip(input.memberNumber())
                                   .map(MembershipProperty::new);
    }
}
