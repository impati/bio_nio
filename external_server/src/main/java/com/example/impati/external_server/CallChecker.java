package com.example.impati.external_server;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CallChecker {

    private final Map<String, Integer> count = new ConcurrentHashMap<>();

    public void count(String key) {
        count.put(key, count.getOrDefault(key, 0) + 1);
    }
}
