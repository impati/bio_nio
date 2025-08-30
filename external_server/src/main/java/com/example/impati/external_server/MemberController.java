package com.example.impati.external_server;

import java.time.Duration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final DelayProperties delayProperties;

    @GetMapping("/members/{memberNumber}/membership")
    public Mono<Boolean> membership(@PathVariable String memberNumber) {
        log.info("delay : {}", delayProperties.membership());
        return Mono.just(true)
                   .delayElement(Duration.ofMillis(delayProperties.membership()));
    }
}
