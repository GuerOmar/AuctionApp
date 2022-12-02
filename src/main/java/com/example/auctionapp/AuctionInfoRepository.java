package com.example.auctionapp;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class AuctionInfoRepository {
    private Map<UUID,AuctionInfo> auctionInfos = new HashMap<>();
    public synchronized   void  addAuction(AuctionInfo auction){
        auctionInfos.put(auction.getId(),auction);
    }

    public AuctionInfo getById(UUID id){
        AuctionInfo auction = auctionInfos.get(id);
        if (auction == null) {
            throw new IllegalArgumentException("Auction not exists");
        }
        return auction;
    }
}
