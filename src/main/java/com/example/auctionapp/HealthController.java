package com.example.auctionapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public synchronized String testHealth(){
        return "Ok";
    }
}
