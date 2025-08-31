package com.example.impati.client;

import com.example.impati.model.Coupon;
import com.example.impati.model.MemberCoupon;
import com.example.impati.model.ReactiveCouponRepository;
import com.example.impati.model.ReactiveMemberCouponRepository;
import com.example.impati.model.ReactiveShopCouponClient;
import com.example.impati.model.ShopCoupon;
import com.example.impati.model.client.ReactiveExternalCouponClient;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class ExternalCouponClientAdaptor implements ReactiveCouponRepository,
                                                    ReactiveShopCouponClient,
                                                    ReactiveMemberCouponRepository,
                                                    ReactiveExternalCouponClient {

    private final WebClient client;

    public ExternalCouponClientAdaptor(WebClient.Builder clientBuilder, ExternalCouponProperty externalCouponProperty) {
        this.client = clientBuilder.baseUrl(externalCouponProperty.baseUrl()).build();
    }

    /**
     * GET /members/{memberNumber}/issuable?couponIds=1&couponIds=2...
     * 응답: List<Long>
     */
    @Override
    public Mono<List<Long>> issuable(String memberNumber, List<Long> couponIds) {
        return client.get()
                     .uri(uriBuilder -> uriBuilder
                             .path("/members/{memberNumber}/issuable")
                             .queryParam("couponIds", couponIds) // List 자동 전개
                             .build(memberNumber))
                     .retrieve()
                     .bodyToMono(new ParameterizedTypeReference<List<Long>>() {
                     })
                     .doOnSubscribe(s -> log.debug("[issuable] memberNumber={}, couponIds={}", memberNumber, couponIds))
                     .doOnSuccess(ids -> log.debug("[issuable] result size={}", ids == null ? 0 : ids.size()));
    }

    /**
     * GET /members/{memberNumber}/memberCoupons
     * 응답: List<MemberCouponResponse>
     */
    @Override
    public Mono<List<MemberCoupon>> findByMember(String memberNumber) {
        return client.get()
                     .uri(uriBuilder -> uriBuilder
                             .path("/members/{memberNumber}/memberCoupons")
                             .build(memberNumber))
                     .retrieve()
                     .bodyToMono(new ParameterizedTypeReference<List<MemberCoupon>>() {
                     })
                     .doOnSubscribe(s -> log.debug("[findByMember] memberNumber={}", memberNumber))
                     .doOnSuccess(res -> log.debug("[findByMember] result size={}", res == null ? 0 : res.size()));
    }

    /**
     * GET /shops/{shopNumber}/shop-coupons
     * 응답: List<ShopCouponResponse>
     */
    @Override
    public Mono<List<ShopCoupon>> getShopCoupon(String shopNumber) {
        return client.get()
                     .uri(uriBuilder -> uriBuilder
                             .path("/shops/{shopNumber}/shop-coupons")
                             .build(shopNumber))
                     .retrieve()
                     .bodyToMono(new ParameterizedTypeReference<List<ShopCoupon>>() {
                     })
                     .doOnSubscribe(s -> log.debug("[getShopCoupon] shopNumber={}", shopNumber))
                     .doOnSuccess(res -> log.debug("[getShopCoupon] result size={}", res == null ? 0 : res.size()));
    }

    /**
     * GET /members/{memberNumber}/coupons
     * 응답: List<CouponResponse>
     */
    @Override
    public Mono<List<Coupon>> findBy(String memberNumber) {
        return client.get()
                     .uri(uriBuilder -> uriBuilder
                             .path("/members/{memberNumber}/coupons")
                             .build(memberNumber))
                     .retrieve()
                     .bodyToMono(new ParameterizedTypeReference<List<Coupon>>() {
                     })
                     .doOnSubscribe(s -> log.debug("[findBy] memberNumber={}", memberNumber))
                     .doOnSuccess(res -> log.debug("[findBy] result size={}", res == null ? 0 : res.size()));
    }
}
