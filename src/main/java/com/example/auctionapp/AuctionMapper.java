package com.example.auctionapp;

import java.time.Duration;
import java.util.Date;
import java.util.UUID;

public class AuctionMapper {
    private UUID id;
    private Date startDate;
    private Duration duration;
    private  int sellerId;
    private int bidderId;
    private  double bid;
    private boolean expired;

    public AuctionMapper(UUID id, Date startDate, Duration duration, int sellerId, int bidderId, double bid, boolean expired) {
        this.id = id;
        this.startDate = startDate;
        this.duration = duration;
        this.sellerId = sellerId;
        this.bidderId = bidderId;
        this.bid = bid;
        this.expired = expired;
    }

    public UUID getId() {
        return id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Duration getDuration() {
        return duration;
    }

    public int getSellerId() {
        return sellerId;
    }

    public int getBidderId() {
        return bidderId;
    }

    public double getBid() {
        return bid;
    }

    public boolean isExpired() {
        return expired;
    }
}
