package com.example.auctionapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/auction/{auctionId}")
public class AuctionController {
    private final Logger log = LoggerFactory.getLogger(AuctionConsumer.class);

    @Autowired
    AuctionRepository auctionRepository;


    @Autowired
    AuctionInfoRepository auctionInfoRepository;
    @GetMapping()
    public synchronized Auction getById(@PathVariable String auctionId) {
        try {
            log.info(String.valueOf(auctionRepository.getById(UUID.fromString(auctionId))));
            return auctionRepository.getById(UUID.fromString(auctionId));
        }
        catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "auction not found"
            );
        }
    }
}
