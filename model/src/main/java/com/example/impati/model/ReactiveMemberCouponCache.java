package com.example.impati.model;

import java.util.List;
import java.util.Optional;
import reactor.core.publisher.Mono;

public interface ReactiveMemberCouponCache {

    Mono<Optional<List<MemberCoupon>>> findByMember(String memberNumber);

    Mono<Void> putMemberCoupon(String memberNumber, List<MemberCoupon> memberCoupons);
}
