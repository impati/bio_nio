package com.example.impati.external_server;

import java.time.Duration;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

// TPS : 1500

@Slf4j
@RestController
@RequiredArgsConstructor
public class ShopController {

    private final DelayProperties delayProperties;

    @GetMapping("/shopNumbers/{shopNumber}/franchise")
    public Mono<String> getFranchise(@PathVariable String shopNumber) {
        return Mono.just(shopNumber)
                   .delayElement(Duration.ofMillis(delayProperties.shopFranchise()));
    }

    @GetMapping("/shopNumbers/{shopNumber}/categories")
    public Mono<List<String>> getCategories(@PathVariable String shopNumber) {
        return Mono.just(List.of("jp", "kr"))
                   .delayElement(Duration.ofMillis(delayProperties.shopFranchise()));
    }
}
