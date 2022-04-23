package com.deemo.mq.spring;

// import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class JmsSpringTopicProduce {

    @Autowired
    private JmsTemplate jmsTemplate;

    // public static void main(String[] args) {
    //     ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    //     JmsSpringTopicProduce produce = applicationContext.getBean(JmsSpringTopicProduce.class);
    //
    //     produce.jmsTemplate.send(session -> session.createTextMessage("spring-deemo-topic-text"));
    //
    //     System.out.println("End.");
    // }
}
