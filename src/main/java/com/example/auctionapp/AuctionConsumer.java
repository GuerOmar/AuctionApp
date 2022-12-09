package com.example.auctionapp;

import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConsumerSeekAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AuctionConsumer  implements  ConsumerSeekAware {
    public static final String TOPIC = "auction.event";
    public static final String TOPIC_TRANSACTION = "transaction";
    public static final String TOPIC_ALERT = "bid.alert";
    private final Logger log = LoggerFactory.getLogger(AuctionConsumer.class);


    @Autowired
    AuctionRepository auctionRepository;
    @Autowired
    AuctionInfoRepository  auctionInfoRepository;
    @Autowired
    AuctionService auctionService;

    @Autowired
    KafkaTemplate<String,Transaction> kafkaTemplate;
    @Autowired
    KafkaTemplate<String,Auction> kafkaTemplateAlert;


    @Override
    public void onPartitionsAssigned(Map<TopicPartition, Long> assignments, ConsumerSeekAware.ConsumerSeekCallback callback) {
        callback.seekToEnd(assignments.keySet());
    }

    @KafkaListener(id = "hgog-chat", topics = TOPIC)
    public synchronized void received(Auction auction) throws Exception {
            log.info(String.valueOf(auction.getAuctionId()));

            if(!auction.getType().equals("FINISH")) {
                //log.info(String.valueOf(auctionService.getById(auction.getAuctionId())));
                Auction auc = auctionRepository.getById(auction.getAuctionId());
                AuctionInfo auctionInfo = auctionInfoRepository.getById(auction.getAuctionId());
                if(auc.getBid() >= auction.getBid() ||     (auctionInfo.getStartDate().toInstant().plusSeconds(auctionInfo.getDuration().toSeconds()).compareTo(auction.getTs()) > 0)){
                    log.info("ERROR : " + auction.getAuctionId());
                    kafkaTemplateAlert.send(TOPIC_ALERT,auction);
                }
                auctionRepository.addAuction(auction);
                auctionInfoRepository.addAuction(auctionService.getById(auction.getAuctionId()));
            }
            else{
                log.info("FINISHED "+String.valueOf(auction.getAuctionId())+" "+String.valueOf(auction.getTs()));
                Auction auc = auctionRepository.getById(auction.getAuctionId());
                auc.setType("FINISH");
                AuctionInfo auctionInfo = auctionInfoRepository.getById(auction.getAuctionId());
                kafkaTemplate.send(TOPIC_TRANSACTION,new Transaction(auctionInfo.getSellerId(),auc.getBidderId(),auc.getBid()));
                log.info("FINISHED "+String.valueOf(auc.getAuctionId())+" "+String.valueOf(auc.getTs()));

            }

    }

}
