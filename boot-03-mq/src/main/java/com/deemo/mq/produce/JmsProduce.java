package com.deemo.mq.produce;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsProduce {
    private static final String QUEUE_NAME = "deemo_queue_01";

    /**
     * -DAMQ_HOST=10.10.10.103
     */
    public static void main(String[] args) throws JMSException {
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
        MessageProducer producer = session.createProducer(queue);

        // 非持久化
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        // 持久化
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);

        TextMessage textMessage;
        MapMessage mapMessage;
        for (int i = 0; i < 3; i++) {
            // TextMessage
            textMessage = session.createTextMessage("Deemo Message " + i);
            // 附加消息属性
            if (i == 2) {
                textMessage.setBooleanProperty("ready " + i, true);
            }
            producer.send(textMessage);

            // MapMessage
            mapMessage = session.createMapMessage();
            mapMessage.setBoolean("ready " + i, true);
        }

        // 关闭资源
        producer.close();
        session.close();
        connection.close();

        System.out.println("End.");
    }
}
