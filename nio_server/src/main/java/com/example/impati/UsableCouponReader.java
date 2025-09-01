package com.example.impati;

import com.example.impati.model.Coupon;
import com.example.impati.model.MemberCoupon;
import com.example.impati.model.ReactiveCouponCache;
import com.example.impati.model.ReactiveCouponRepository;
import com.example.impati.model.ReactiveExternalCouponCache;
import com.example.impati.model.ReactiveMemberCouponCache;
import com.example.impati.model.ReactiveMemberCouponRepository;
import com.example.impati.model.ReactiveShopCouponCache;
import com.example.impati.model.ReactiveShopCouponClient;
import com.example.impati.model.ShopCoupon;
import com.example.impati.model.client.ReactiveExternalCouponClient;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@SuppressWarnings("OptionalIsPresent")
@RequiredArgsConstructor
@Component
public class UsableCouponReader {

    private final ReactiveCouponRepository couponRepository;
    private final ReactiveCouponCache couponCache;

    private final ReactiveMemberCouponRepository memberCouponRepository;
    private final ReactiveMemberCouponCache memberCouponCache;

    private final ReactiveShopCouponClient shopCouponClient;
    private final ReactiveShopCouponCache shopCouponCache;

    private final ReactiveExternalCouponClient externalCouponClient;
    private final ReactiveExternalCouponCache externalCouponCache;

    // @formatter:off

    public Mono<List<Coupon>> read(String memberNumber, String shopNumber) {


        Mono<List<Coupon>> couponMono = couponCache.get(memberNumber)
                   .flatMap(coupons -> {
                       if(coupons.isPresent()){
                            return Mono.just(coupons.get());
                       }


                       return couponRepository.findBy(memberNumber)
                                              .flatMap(fetchedCoupons -> couponCache.putCoupon(memberNumber, fetchedCoupons).thenReturn(fetchedCoupons));
                   });

        Mono<List<MemberCoupon>> memberCouponMono = memberCouponCache.findByMember(memberNumber)
                .flatMap(memberCoupons -> {
                    if(memberCoupons.isPresent()){
                        return Mono.just(memberCoupons.get());
                    }

                    return memberCouponRepository.findByMember(memberNumber)
                                                .flatMap(fetchedMemberCoupons -> memberCouponCache.putMemberCoupon(memberNumber, fetchedMemberCoupons)
                                                                                                 .thenReturn(fetchedMemberCoupons));
                });



        Mono<List<Long>> shopCouponMono = shopCouponCache.getShopCoupon(shopNumber)
                   .flatMap(coupons -> {
                       if(coupons.isPresent()){
                            return Mono.just(coupons.get());
                       }

                          return shopCouponClient.getShopCoupon(shopNumber)
                                                 .flatMap(fetchedCoupons -> shopCouponCache.putShopCoupon(shopNumber, fetchedCoupons).thenReturn(fetchedCoupons));
                       }).flatMap(shopCoupons -> {
                            if(shopCoupons.isEmpty()){
                                 return Mono.just(List.of());
                            }

                            return externalCouponCache.get(memberNumber,shopCoupons.stream().map(ShopCoupon::couponId).toList())
                                                    .flatMap(coupons -> {
                                                        if(coupons.isPresent()){
                                                            return Mono.just(coupons.get());
                                                        }

                                                        List<Long> couponIds = shopCoupons.stream().map(ShopCoupon::couponId).toList();
                                                        return externalCouponClient.issuable(memberNumber,couponIds)
                                                                                    .flatMap(fetchedCoupons -> externalCouponCache.put(memberNumber,couponIds)
                                                                                                                            .thenReturn(fetchedCoupons));
                                                    });
                });


        return Mono.zip(couponMono, shopCouponMono,memberCouponMono)
                   .map(tuple -> {
                       List<Coupon> coupons = tuple.getT1();
                       List<Long> shopCouponIds = tuple.getT2();
                       List<MemberCoupon> memberCoupons = tuple.getT3();

                       log.debug("coupon: {}, shopCoupon: {}, memberCoupon: {}", coupons, shopCouponIds, memberCoupons);
                       return coupons.stream().toList();
                   });
    }

    // @formatter:on
}
