package com.example.impati.client;

import com.example.impati.model.client.MemberClient;
import com.example.impati.model.client.ReactiveMemberClient;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class MemberClientAdaptor implements MemberClient, ReactiveMemberClient {

    private final WebClient client;

    public MemberClientAdaptor(WebClient.Builder webClientBuilder, MemberClientProperty memberClientProperty) {
        this.client = webClientBuilder.baseUrl(memberClientProperty.baseUrl()).build();
    }

    @Override
    public Mono<Boolean> isMemberShip(final String memberNumber) {
        return client.get().uri("/members/{memberNumber}/membership", memberNumber)
                     .retrieve()
                     .bodyToMono(Boolean.class);
    }

    @Override
    public boolean isMemberShipB(final String memberNumber) {
        return Boolean.TRUE.equals(isMemberShip(memberNumber).block());
    }
}
