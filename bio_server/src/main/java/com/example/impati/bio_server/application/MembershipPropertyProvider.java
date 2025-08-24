package com.example.impati.bio_server.application;

import com.example.impati.model.Coupon;
import com.example.impati.model.Input;
import com.example.impati.model.client.MemberClient;
import com.example.impati.model.property.MembershipProperty;
import com.example.impati.model.property_loader.PropertyProvider;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MembershipPropertyProvider implements PropertyProvider<MembershipProperty> {

    private final MemberClient memberClient;

    @Override
    public MembershipProperty provide(final Input input, final List<Coupon> coupons) {
        return new MembershipProperty(
                memberClient.isMemberShipB(input.memberNumber())
        );
    }
}
