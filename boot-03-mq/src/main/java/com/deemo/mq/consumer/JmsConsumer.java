package com.deemo.mq.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class JmsConsumer {

    private static final String QUEUE_NAME = "deemo_queue_01";

    /**
     * -DAMQ_HOST=10.10.10.103
     */
    public static void main(String[] args) throws JMSException, IOException {
        // 1. 创建连接工厂，使用默认用户名和密码
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        // 2. 获取连接
        Connection connection = connectionFactory.createConnection();
        // 启动
        connection.start();

        // 3. 创建会话，参数1：事务，参数2：ACK签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4. 目的地（队列/主题）
        Queue queue = session.createQueue(QUEUE_NAME);

        // 5. 创建生产者
        MessageConsumer consumer = session.createConsumer(queue);

        // receive
        // Message message;
        // while (true) {
        //     message = consumer.receive();
        //     // 暂时未搞懂有啥用，不按字面理解出牌
        //     // message = consumer.receiveNoWait();
        //     if (message == null) {
        //         break;
        //     }
        //
        //     System.out.println("Receive: " + ((TextMessage) message).getText());
        // }

        consumer.setMessageListener(message -> {
            if (message instanceof TextMessage) {
                try {
                    System.out.println("Receive: " + ((TextMessage) message).getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        System.in.read();

        // 关闭资源
        consumer.close();
        session.close();
        connection.close();

        System.out.println("End.");
    }

}
