package com.deemo.mq.broker;

import org.apache.activemq.broker.BrokerService;

public class DeemoBroker {

    public static void main(String[] args) throws Exception {
        BrokerService brokerService = new BrokerService();
        brokerService.setUseJmx(true);
        brokerService.addConnector("tcp://localhost:61616");
        brokerService.start();

        System.in.read();
    }

}
