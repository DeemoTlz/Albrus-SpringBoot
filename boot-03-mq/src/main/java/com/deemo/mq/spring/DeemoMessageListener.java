package com.deemo.mq.spring;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component
public class DeemoMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                System.out.println("DeemoMessageListener onMessage: " + ((TextMessage) message).getText());
                return;
            }

            System.out.println(message.getJMSType());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
