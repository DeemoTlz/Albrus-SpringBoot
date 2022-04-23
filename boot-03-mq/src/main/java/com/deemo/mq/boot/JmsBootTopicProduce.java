package com.deemo.mq.boot;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.DeliveryMode;
import javax.jms.Queue;
import javax.jms.Topic;
import java.util.UUID;

// @Service
public class JmsBootTopicProduce {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Resource
    private Topic topic;


    public void produce() {
        jmsMessagingTemplate.convertAndSend(topic, "Boot Deemo Topic Message " + UUID.randomUUID().toString().substring(0, 8));
    }

    // 每三秒钟调用一次
    @Scheduled(fixedDelay = 3000)
    public void produceScheduled() {
        jmsMessagingTemplate.convertAndSend(topic, "Boot Deemo Scheduled Topic Message " + UUID.randomUUID().toString().substring(0, 8));
        System.out.println("Scheduled Topic send End.");
    }

}
