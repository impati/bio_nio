package com.example.impati.external_server;

import java.time.Duration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final DelayProperties delayProperties;

    @GetMapping("/order-histories/memberNumber/{memberNumber}")
    public Mono<Boolean> hasOrderHistory(@PathVariable String memberNumber) {
        return Mono.just("firstMember".equals(memberNumber))
                   .delayElement(Duration.ofMillis(delayProperties.hasOrderHistory()));
    }

    @GetMapping("/orders/{orderNumber}")
    public Mono<OrderDetail> getOrder(@PathVariable String orderNumber) {
        return Mono.just(new OrderDetail("DELIVERY"))
                   .delayElement(Duration.ofMillis(delayProperties.getOrder()));
    }

    public record OrderDetail(String deliveryType) {

    }
}
