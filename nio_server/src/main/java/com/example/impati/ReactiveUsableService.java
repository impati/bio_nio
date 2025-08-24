package com.example.impati;

import com.example.impati.model.Coupon;
import com.example.impati.model.Input;
import com.example.impati.model.ReactiveCouponRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
public class ReactiveUsableService {

    private final ReactiveCouponRepository couponRepository;
    private final DelegatingPropertyProvider delegatingPropertyProvider;
    private final UseValidator useValidator;

    public Mono<List<Coupon>> usable(Input input) {
        return couponRepository
                .findBy(input.memberNumber())
                .flatMap(coupon -> delegatingPropertyProvider
                        .provide(input, coupon)
                        .map(properties -> useValidator.validate(properties, coupon)));
    }
}
