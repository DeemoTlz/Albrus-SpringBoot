package com.deemo.mq.boot;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.Topic;

@Component
@EnableJms
public class ConfigBean {

    @Value("${deemo.queue}")
    private String DeemoQueue;

    @Value("${deemo.topic}")
    private String DeemoTopic;

    @Bean
    public Queue queue() {
        return new ActiveMQQueue(DeemoQueue);
    }

    @Bean
    public Topic topic() {
        return new ActiveMQTopic(DeemoTopic);
    }

}
