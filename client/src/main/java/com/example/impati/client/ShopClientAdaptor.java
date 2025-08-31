package com.example.impati.client;

import com.example.impati.model.client.ReactiveShopClient;
import com.example.impati.model.client.ShopClient;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ShopClientAdaptor implements ShopClient, ReactiveShopClient {

    private final WebClient client;

    public ShopClientAdaptor(WebClient.Builder webClientBuilder, ShopClientProperty property) {
        this.client = webClientBuilder.baseUrl(property.baseUrl()).build();
    }

    @Override
    public Mono<String> franchise(final String shopNumber) {
        return client.get().uri("/shopNumbers/{shopNumber}/franchise", shopNumber)
                     .retrieve()
                     .bodyToMono(String.class);
    }

    @Override
    public Mono<List<String>> categories(final String shopNumber) {
        return client.get().uri("/shopNumbers/{shopNumber}/categories", shopNumber)
                     .retrieve()
                     .bodyToMono(new ParameterizedTypeReference<List<String>>() {
                     });
    }

    @Override
    public String getFranchise(final String shopNumber) {
        return franchise(shopNumber).block();
    }

    @Override
    public List<String> getCategories(final String shopNumber) {
        return categories(shopNumber).block();
    }
}
