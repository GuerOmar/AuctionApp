package com.example.auctionapp;

import java.time.Instant;
import java.util.UUID;

public class Auction {
 private String type;
private int bidderId;
    private double bid;
    private UUID auctionId;
    private Instant ts;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBidderId() {
        return bidderId;
    }

    public void setBidderId(int bidderId) {
        this.bidderId = bidderId;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public UUID getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(UUID auctionId) {
        this.auctionId = auctionId;
    }

    public Instant getTs() {
        return ts;
    }

    public void setTs(Instant ts) {
        this.ts = ts;
    }



}
