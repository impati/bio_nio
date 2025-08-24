package com.example.impati.client;

import com.example.impati.model.OrderDetail;
import com.example.impati.model.client.OrderClient;
import com.example.impati.model.client.ReactiveOrderClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class OrderClientAdaptor implements OrderClient, ReactiveOrderClient {

    private final WebClient webClient;

    public OrderClientAdaptor(WebClient.Builder webClientBuilder, OrderClientProperty orderClientProperty) {
        this.webClient = webClientBuilder.baseUrl(orderClientProperty.baseUrl()).build();

    }

    @Override
    public boolean hasOrderHistoryB(final String memberNumber) {
        return Boolean.TRUE.equals(hasOrderHistory(memberNumber).block());
    }

    @Override
    public OrderDetail getOrderDetailB(final String orderNumber) {
        return getOrderDetail(orderNumber).block();
    }

    @Override
    public Mono<Boolean> hasOrderHistory(final String memberNumber) {
        return webClient.get().uri("/order-histories/memberNumber/{memberNumber}", memberNumber)
                .retrieve()
                .bodyToMono(Boolean.class);
    }

    @Override
    public Mono<OrderDetail> getOrderDetail(final String orderNumber) {
        return webClient.get().uri("/orders/{orderNumber}", orderNumber)
                .retrieve()
                .bodyToMono(OrderDetail.class);
    }
}
