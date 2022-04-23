package com.deemo.mq.spring;

// import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class JmsSpringProduce {

    @Autowired
    private JmsTemplate jmsTemplate;

    // public static void main(String[] args) {
    //     ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    //     JmsSpringProduce produce = applicationContext.getBean(JmsSpringProduce.class);
    //
    //     produce.jmsTemplate.send(session -> session.createTextMessage("spring-deemo-text"));
    //
    //     System.out.println("End.");
    // }
}
