package com.example.impati;

import com.example.impati.model.Coupon;
import com.example.impati.model.Input;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReactiveUsableService {

    private final UsableCouponReader usableCouponReader;
    private final DelegatingPropertyProvider delegatingPropertyProvider;
    private final UseValidator useValidator;

    public Mono<List<Coupon>> usable(Input input) {
        return usableCouponReader
                .read(input.memberNumber(), input.shopNumber())
                .flatMap(coupon -> delegatingPropertyProvider
                        .provide(input, coupon)
                        .map(properties -> useValidator.validate(properties, coupon)));
    }
}
