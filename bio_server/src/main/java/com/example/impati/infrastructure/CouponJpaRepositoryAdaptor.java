package com.example.impati.infrastructure;

import com.example.impati.model.Coupon;
import com.example.impati.model.CouponRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
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

    @Override
    public Coupon save(Coupon coupon) {
        return couponJpaRepository.save(CouponEntity.from(coupon))
                                  .toDomain();
    }
}
