package com.deemo.mq.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class JmsTopicPersistConsumer {

    private static final String TOPIC_NAME = "deemo_topic_persist_01";

    /**
     * -DAMQ_HOST=10.10.10.103
     */
    public static void main(String[] args) throws JMSException, IOException {
        // 1. 创建连接工厂，使用默认用户名和密码
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        // 2. 获取连接
        Connection connection = connectionFactory.createConnection();
        // 订阅模式，需要指定ID
        connection.setClientID("deemo_topic_consumer_01");
        // 启动
        connection.start();

        // 3. 创建会话，参数1：事务，参数2：ACK签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4. 目的地（队列/主题）
        Topic topic = session.createTopic(TOPIC_NAME);

        // 5. 创建订阅生产者
        TopicSubscriber topicSubscriber = session.createDurableSubscriber(topic, "remark...");

        // receive
        Message message = topicSubscriber.receive();
        while (message != null) {
            System.out.println("Receive: " + ((TextMessage) message).getText());

            message = topicSubscriber.receive(2000L);
        }

        // topicSubscriber.setMessageListener(message -> {
        //     if (message instanceof TextMessage) {
        //         try {
        //             System.out.println("Receive: " + ((TextMessage) message).getText());
        //         } catch (JMSException e) {
        //             e.printStackTrace();
        //         }
        //     }
        // });
        // System.in.read();

        // 关闭资源
        topicSubscriber.close();
        session.close();
        connection.close();

        System.out.println("End.");
    }

}
