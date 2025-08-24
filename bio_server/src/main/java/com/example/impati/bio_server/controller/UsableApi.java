package com.example.impati.bio_server.controller;

import com.example.impati.bio_server.application.UsableService;
import com.example.impati.model.Coupon;
import com.example.impati.model.Input;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
public class UsableApi {

    private final UsableService usableService;

    @GetMapping("/members/{memberNumber}")
    public List<Coupon> usable(@PathVariable String memberNumber,
                               @RequestParam("orderNumber") String orderNumber,
                               @RequestParam("shopNumber") String shopNumber
    ) {

        return usableService.usable(new Input(shopNumber, orderNumber, memberNumber));
    }
}
