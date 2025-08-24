package com.example.impati.external_server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class OrderController {

    @GetMapping("/order-histories/memberNumber/{memberNumber}")
    public Mono<Boolean> hasOrderHistory(@PathVariable String memberNumber) {
        return Mono.just("firstMember".equals(memberNumber));
    }

    @GetMapping("/orders/{orderNumber}")
    public Mono<OrderDetail> getOrder(@PathVariable String orderNumber) {
        return Mono.just(new OrderDetail("DELIVERY"));
    }

    public record OrderDetail(String deliveryType) {

    }
}
