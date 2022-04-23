package com.deemo.mq.boot;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Queue;
import java.util.UUID;

// @Service
public class JmsBootProduce {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Resource
    private Queue queue;


    public void produce() {
        jmsMessagingTemplate.convertAndSend(queue, "Boot Deemo Message " + UUID.randomUUID().toString().substring(0, 8));
    }

    // 每三秒钟调用一次
    @Scheduled(fixedDelay = 3000)
    public void produceScheduled() {
        jmsMessagingTemplate.convertAndSend(queue, "Boot Deemo Scheduled Message " + UUID.randomUUID().toString().substring(0, 8));
        System.out.println("Scheduled send End.");
    }

}
