package com.example.auctionapp;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class AuctionRepository {
    private Map<UUID,Auction> auctions = new HashMap<>();
    public synchronized   void  addAuction(Auction auction){
        auctions.put(auction.getAuctionId(),auction);
    }

    public synchronized Auction getById(UUID id){
        Auction auction = auctions.get(id);
        if (auction == null) {
            throw new IllegalArgumentException("Auction not exists");
        }
        return auction;
    }

}
