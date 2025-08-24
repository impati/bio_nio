package com.example.impati.model.client;

import reactor.core.publisher.Mono;

public interface ReactiveMemberClient {

    Mono<Boolean> isMemberShip(String memberNumber);
}
