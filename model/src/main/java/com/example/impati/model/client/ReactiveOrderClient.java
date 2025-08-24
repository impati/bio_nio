package com.example.impati.model.client;

import com.example.impati.model.OrderDetail;
import reactor.core.publisher.Mono;

public interface ReactiveOrderClient {

    Mono<Boolean> hasOrderHistory(String memberNumber);

    Mono<OrderDetail> getOrderDetail(String orderNumber);
}
