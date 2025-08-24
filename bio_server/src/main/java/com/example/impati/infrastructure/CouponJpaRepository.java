package com.example.impati.infrastructure;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponJpaRepository extends JpaRepository<CouponEntity, Long> {

    List<CouponEntity> findByMemberNumber(String memberNumber);
}
