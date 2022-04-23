package com.deemo.mq.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.TextMessage;

@Component
public class JmsBootTopicConsumer {

    // @Bean(name = "topicListenerFactory")
    public JmsListenerContainerFactory<DefaultMessageListenerContainer> topicListenerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setSubscriptionDurable(true);
        factory.setPubSubDomain(true);
        factory.setClientId("boot-topic-persist-01");
        factory.setConnectionFactory(connectionFactory);

        return factory;
    }

    @JmsListener(destination = "${deemo.topic}")
    // @JmsListener(destination = "${deemo.topic}", containerFactory = "topicListenerFactory")
    public void receive(TextMessage textMessage) throws JMSException {
        System.out.println("Boot Receive Topic: " + textMessage.getText());
    }
}
