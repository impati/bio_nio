package com.example.impati.external_server;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CallCheckController {

    private final CallChecker callChecker;

    @GetMapping("/call-check")
    public Map<String, Integer> callCheck() {
        return callChecker.getCount();
    }
}
