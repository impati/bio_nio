package com.example.impati.controller;

import com.example.impati.application.UsableService;
import com.example.impati.model.Coupon;
import com.example.impati.model.Input;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UsableApi {

    private final UsableService usableService;

    @GetMapping("/members/{memberNumber}/usable-coupons")
    public List<Coupon> usable(@PathVariable String memberNumber,
                               @RequestParam("orderNumber") String orderNumber,
                               @RequestParam("shopNumber") String shopNumber
                              ) {

        return usableService.usable(new Input(shopNumber, orderNumber, memberNumber));
    }
}
