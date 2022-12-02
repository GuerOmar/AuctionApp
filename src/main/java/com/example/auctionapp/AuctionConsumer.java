package com.example.auctionapp;

import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.ConsumerSeekAware;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AuctionConsumer  implements  ConsumerSeekAware {
    public static final String TOPIC = "auction.event";
    private final Logger log = LoggerFactory.getLogger(AuctionConsumer.class);

    @Autowired
    AuctionRepository auctionRepository;
    @Autowired
    AuctionInfoRepository  auctionInfoRepository;
    @Autowired
    AuctionService auctionService;

    @Override
    public void onPartitionsAssigned(Map<TopicPartition, Long> assignments, ConsumerSeekAware.ConsumerSeekCallback callback) {
        callback.seekToEnd(assignments.keySet());
    }

    @KafkaListener(id = "hgog-chat", topics = TOPIC)
    public synchronized void received(Auction auction) throws Exception {
            log.info(String.valueOf(auction.getAuctionId()));
            auctionRepository.addAuction(auction);
            if(!auction.getType().equals("FINISH")) {
                //log.info(String.valueOf(auctionService.getById(auction.getAuctionId())));
                auctionInfoRepository.addAuction(auctionService.getById(auction.getAuctionId()));
            }

    }

}
