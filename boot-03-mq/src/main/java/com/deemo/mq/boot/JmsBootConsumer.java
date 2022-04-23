package com.deemo.mq.boot;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Component
public class JmsBootConsumer {

    @JmsListener(destination = "${deemo.queue}")
    public void receive(TextMessage textMessage) throws JMSException {
        System.out.println("Boot Receive: " + textMessage.getText());
    }
}
