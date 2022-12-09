package com.example.auctionapp;

import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.ConsumerSeekAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AlertConsumer implements  ConsumerSeekAware{

    public static final String TOPIC = "auction.alert";
    private final Logger log = LoggerFactory.getLogger(AlertConsumer.class);
    @Override
    public void onPartitionsAssigned(Map<TopicPartition, Long> assignments, ConsumerSeekAware.ConsumerSeekCallback callback) {
        callback.seekToTimestamp(assignments.keySet(), System.currentTimeMillis() - 10*60*1000);
    }


    @KafkaListener(id = "hgog-alert", topics = TOPIC)
    public synchronized void received(String str) throws Exception {
        log.info("Alert : " +str);
    }
}
