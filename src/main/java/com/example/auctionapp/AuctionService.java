package com.example.auctionapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class AuctionService {
    private final Logger log = LoggerFactory.getLogger(AuctionInfo.class);
    @Autowired
    WebClient webClient;

    @Async
    public AuctionInfo getById(UUID id) {

        return webClient.get().uri("/auction/{auctionid}", id.toString())
                .retrieve()
                .bodyToMono(AuctionInfo.class)
                .onErrorResume(WebClientResponseException.class, ex ->  ex.getRawStatusCode() == 404 ? null : Mono.error(ex))
                .block();
    }



}
