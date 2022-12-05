package com.example.auctionapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public class TransactionService {

    @Autowired
    KafkaTemplate<String,Transaction.class> kafkaTemplate;
}
