package com.example.impati.infrastructure;

import com.example.impati.model.Coupon;
import com.example.impati.model.MemberCoupon;
import com.example.impati.model.ReactiveCouponCache;
import com.example.impati.model.ReactiveExternalCouponCache;
import com.example.impati.model.ReactiveMemberCouponCache;
import com.example.impati.model.ReactiveShopCouponCache;
import com.example.impati.model.ShopCoupon;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class RedisRepository implements ReactiveCouponCache,
                                        ReactiveShopCouponCache,
                                        ReactiveMemberCouponCache,
                                        ReactiveExternalCouponCache {

    private final ReactiveRedisTemplate<String, String> template;
    private final ObjectMapper mapper;

    private static final String KEY_COUPONS_BY_MEMBER = "coupon:member:%s";        // List<Coupon>
    private static final String KEY_MEMBER_COUPONS = "memberCoupon:member:%s";  // List<MemberCoupon>
    private static final String KEY_SHOP_COUPONS = "shopCoupon:shop:%s";      // List<ShopCoupon>
    private static final String KEY_ISSUABLE = "ext:issuable:%s:%s";      // List<Long> (member + ids)

    // --- Coupon (by member) -----------------------------------------------------

    @Override
    public Mono<Optional<List<Coupon>>> get(String memberNumber) {
        final String key = String.format(KEY_COUPONS_BY_MEMBER, memberNumber);
        return readJson(key, new TypeReference<List<Coupon>>() {
        });
    }

    @Override
    public Mono<Void> putCoupon(String memberNumber, List<Coupon> coupons) {
        final String key = String.format(KEY_COUPONS_BY_MEMBER, memberNumber);
        return writeJson(key, coupons);
    }

    // --- External issuable (member + candidate ids) -----------------------------

    @Override
    public Mono<Void> put(String memberNumber, List<Long> couponIds) {
        final String key = issuableKey(memberNumber, couponIds);
        return writeJson(key, couponIds);
    }

    @Override
    public Mono<Optional<List<Long>>> get(String memberNumber, List<Long> couponIds) {
        final String key = issuableKey(memberNumber, couponIds);
        return readJson(key, new TypeReference<List<Long>>() {
        });
    }

    private String issuableKey(String memberNumber, List<Long> couponIds) {
        final String joined = couponIds.stream().map(String::valueOf).collect(Collectors.joining(","));
        return String.format(KEY_ISSUABLE, memberNumber, joined);
    }

    // --- MemberCoupon (by member) -----------------------------------------------

    @Override
    public Mono<Optional<List<MemberCoupon>>> findByMember(String memberNumber) {
        final String key = String.format(KEY_MEMBER_COUPONS, memberNumber);
        return readJson(key, new TypeReference<List<MemberCoupon>>() {
        });
    }

    @Override
    public Mono<Void> putMemberCoupon(String memberNumber, List<MemberCoupon> memberCoupons) {
        final String key = String.format(KEY_MEMBER_COUPONS, memberNumber);
        return writeJson(key, memberCoupons);
    }

    // --- ShopCoupon (by shop) ---------------------------------------------------

    @Override
    public Mono<Optional<List<ShopCoupon>>> getShopCoupon(String shopNumber) {
        final String key = String.format(KEY_SHOP_COUPONS, shopNumber);
        return readJson(key, new TypeReference<List<ShopCoupon>>() {
        });
    }

    @Override
    public Mono<Void> putShopCoupon(String shopNumber, List<ShopCoupon> shopCoupons) {
        final String key = String.format(KEY_SHOP_COUPONS, shopNumber);
        return writeJson(key, shopCoupons);
    }

    // --- JSON helpers -----------------------------------------------------------

    private <T> Mono<Optional<T>> readJson(String key, TypeReference<T> typeRef) {
        return template.opsForValue().get(key)
                       .map(json -> {
                           try {
                               T value = mapper.readValue(json, typeRef);
                               return Optional.ofNullable(value);     // Optional<T>
                           } catch (Exception e) {
                               return Optional.<T>empty();            // <-- 타입 명시
                           }
                       })
                       .defaultIfEmpty(Optional.<T>empty());          // <-- 타입 명시
    }

    private Mono<Void> writeJson(String key, Object value) {
        try {
            String json = mapper.writeValueAsString(value);
            return template.opsForValue().set(key, json, Duration.of(1000, ChronoUnit.MILLIS)).then();
        } catch (Exception e) {
            return Mono.error(e);
        }
    }
}
