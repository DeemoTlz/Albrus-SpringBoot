package com.deemo.mq.spring;

// import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Service
public class JmsSpringTopicConsumer {

    @Autowired
    private JmsTemplate jmsTemplate;

    // public static void main(String[] args) throws JMSException {
    //     ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    //     JmsSpringTopicConsumer consumer = applicationContext.getBean(JmsSpringTopicConsumer.class);
    //
    //     // String text = (String) consumer.jmsTemplate.receiveAndConvert();
    //     TextMessage message;
    //     while(true) {
    //         message = (TextMessage) consumer.jmsTemplate.receive();
    //         if (message == null) {
    //             break;
    //         }
    //
    //         System.out.println("Spring topic consumer receive: " + message.getText());
    //     }
    //
    //     System.out.println("End.");
    // }
}
