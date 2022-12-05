package com.example.auctionapp;

public class Transaction {
    private String auctionner = "OmarHedi";
    private int sellerId;
    private int buyerId;
    private double amount;

    public Transaction(int sellerId, int buyerId, double amount) {
        this.sellerId = sellerId;
        this.buyerId = buyerId;
        this.amount = amount;
    }

    public String getAuctionner() {
        return auctionner;
    }

    public void setAuctionner(String auctionner) {
        this.auctionner = auctionner;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
