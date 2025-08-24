package com.example.impati.bio_server.infrastructure;

import com.example.impati.model.Coupon;
import com.example.impati.model.CouponRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CouponJpaRepositoryAdaptor implements CouponRepository {

    private final CouponJpaRepository couponJpaRepository;

    @Override
    public List<Coupon> findBy(final String memberNumber) {
        return couponJpaRepository.findByMemberNumber(memberNumber)
                .stream()
                .map(CouponEntity::toDomain)
                .toList();
    }
}
