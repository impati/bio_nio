package com.example.impati.external_server;

import java.time.Duration;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CouponController {

    private final DelayProperties delayProperties;
    private final CallChecker callChecker;

    @GetMapping("/members/{memberNumber}/issuable")
    public Mono<List<Long>> issuable(@PathVariable String memberNumber, @RequestParam List<Long> couponIds) {
        callChecker.count("issuable");
        log.info("issuable memberNumber={}, couponIds={}", memberNumber, couponIds);
        return Mono.just(couponIds)
                   .delayElement(Duration.ofMillis(delayProperties.couponIssuable()));
    }

    @GetMapping("/members/{memberNumber}/memberCoupons")
    public Mono<List<MemberCouponResponse>> memberCoupons(@PathVariable String memberNumber) {
        callChecker.count("memberCoupons");
        log.info("memberCoupons memberNumber={}", memberNumber);
        return Mono.just(List.of(new MemberCouponResponse(1L, "ISSUED"),
                                 new MemberCouponResponse(2L, "ISSUED")))
                   .delayElement(Duration.ofMillis(delayProperties.memberCoupon()));
    }

    @GetMapping("/shops/{shopNumber}/shop-coupons")
    public Mono<List<ShopCouponResponse>> shopCoupons(@PathVariable String shopNumber) {
        callChecker.count("shopCoupons");

        log.info("shopCoupons shopNumber={}", shopNumber);
        return Mono.just(List.of(new ShopCouponResponse(1L, shopNumber),
                                 new ShopCouponResponse(2L, shopNumber)))
                   .delayElement(Duration.ofMillis(delayProperties.shopCoupon()));
    }

    @GetMapping("/members/{memberNumber}/coupons")
    public Mono<List<CouponResponse>> coupons(@PathVariable String memberNumber) {
        callChecker.count("coupons");
        log.info("coupons memberNumber={}", memberNumber);
        return Mono.just(List.of(
                           new CouponResponse("1", memberNumber, List.of("A", "B"), List.of("kr", "jp"), "DELIVERY", true, true),
                           new CouponResponse("2", memberNumber, List.of("A", "B"), List.of("kr", "jp"), "DELIVERY", true, true),
                           new CouponResponse("3", memberNumber, List.of("A", "B"), List.of("kr", "jp"), "DELIVERY", true, true),
                           new CouponResponse("4", memberNumber, List.of("A", "B"), List.of("kr", "jp"), "DELIVERY", true, true),
                           new CouponResponse("5", memberNumber, List.of("A", "B"), List.of("kr", "jp"), "DELIVERY", true, true),
                           new CouponResponse("6", memberNumber, List.of("A", "B"), List.of("kr", "jp"), "DELIVERY", true, true),
                           new CouponResponse("7", memberNumber, List.of("A", "B"), List.of("kr", "jp"), "DELIVERY", true, true),
                           new CouponResponse("8", memberNumber, List.of("A", "B"), List.of("kr", "jp"), "DELIVERY", true, true),
                           new CouponResponse("9", memberNumber, List.of("A", "B"), List.of("kr", "jp"), "DELIVERY", true, true),
                           new CouponResponse("10", memberNumber, List.of("A", "B"), List.of("kr", "jp"), "DELIVERY", true, true),
                           new CouponResponse("11", memberNumber, List.of("A", "B"), List.of("kr", "jp"), "DELIVERY", true, true)))
                   .delayElement(Duration.ofMillis(delayProperties.shopCoupon()));
    }

    public record CouponResponse(
            String couponId,

            String memberNumber,

            // 가게
            List<String> usableFranchiseNumber,
            List<String> categories,

            // 주문
            String deliveryType,
            boolean onlyFirstOrder,

            // 회원
            boolean onlyMembership
    ) {

    }

    public record ShopCouponResponse(
            Long couponId,
            String shopNumber
    ) {

    }

    public record MemberCouponResponse(
            Long memberCouponId,
            String status
    ) {

    }
}
