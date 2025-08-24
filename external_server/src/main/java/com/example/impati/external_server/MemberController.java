package com.example.impati.external_server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class MemberController {

    @GetMapping("/members/{memberNumber}/membership")
    public Mono<Boolean> membership(@PathVariable String memberNumber) {
        return Mono.just(true);
    }
}
