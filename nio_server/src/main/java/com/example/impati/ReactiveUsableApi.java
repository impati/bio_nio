package com.example.impati;

import com.example.impati.model.Coupon;
import com.example.impati.model.Input;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
public class ReactiveUsableApi {

    private final ReactiveUsableService usableService;

    @GetMapping("/members/{memberNumber}")
    public Mono<List<Coupon>> usable(@PathVariable String memberNumber,
                                     @RequestParam("orderNumber") String orderNumber,
                                     @RequestParam("shopNumber") String shopNumber
                                    ) {

        return usableService.usable(new Input(memberNumber, orderNumber, shopNumber));
    }
}
