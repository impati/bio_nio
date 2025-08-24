package com.example.impati.external_server;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ShopController {

    @GetMapping("/shopNumbers/{shopNumber}/franchise")
    public Mono<String> getFranchise(@PathVariable String shopNumber) {
        return Mono.just(shopNumber);
    }

    @GetMapping("/shopNumbers/{shopNumber}/categories")
    public Mono<List<String>> getCategories(@PathVariable String shopNumber) {
        return Mono.just(List.of("jp","kr"));
    }
}
