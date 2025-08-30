package com.example.impati.model;

import java.util.List;
import reactor.core.publisher.Mono;

public interface ReactiveMemberCouponRepository {

    Mono<List<MemberCoupon>> findByMember(String memberNumber);
}
