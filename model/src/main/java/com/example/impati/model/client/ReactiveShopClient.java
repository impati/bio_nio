package com.example.impati.model.client;

import java.util.List;
import reactor.core.publisher.Mono;

public interface ReactiveShopClient {

    Mono<String> franchise(String shopNumber);

    Mono<List<String>> categories(String shopNumber);
}
