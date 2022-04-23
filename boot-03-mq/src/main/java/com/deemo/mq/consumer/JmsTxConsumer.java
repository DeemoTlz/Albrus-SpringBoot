package com.deemo.mq.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class JmsTxConsumer {

    private static final String QUEUE_NAME = "deemo_tx_queue_01";

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

        // 3. 创建会话，开启事务
        // Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        // Session session = connection.createSession(true, Session.DUPS_OK_ACKNOWLEDGE);
        // 4. 目的地（队列/主题）
        Queue queue = session.createQueue(QUEUE_NAME);

        // 5. 创建生产者
        MessageConsumer consumer = session.createConsumer(queue);

        // receive
        Message message;
        while (true) {
            message = consumer.receive(4000L);
            if (message == null) {
                break;
            }

            System.out.println("Receive: " + ((TextMessage) message).getText());
            // 未开启事务时，手动确认签收；开启事务时，手动确认签收无意义
            message.acknowledge();
            // 开启事务时，必须需要手动 commit，防止重复消费
            // session.commit();
        }

        // consumer.setMessageListener(message -> {
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
        consumer.close();
        session.close();
        connection.close();

        System.out.println("End.");
    }

}
