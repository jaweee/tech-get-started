package com.jaweee.rabbitmq.directexchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

/**
 * @author: me
 * @title: EmitLogDirect
 * @description:
 * @date: 2022/6/12 16:55
 */
public class EmitLogDirect {

    public static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try(Connection connection = factory.newConnection(); Channel channel = connection.createChannel()){
            channel.exchangeDeclare(EXCHANGE_NAME, "direct");

            String severity = args[0];
            String message = args[1];

            channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + severity + "':'" + message + "'");
        }
    }
}
