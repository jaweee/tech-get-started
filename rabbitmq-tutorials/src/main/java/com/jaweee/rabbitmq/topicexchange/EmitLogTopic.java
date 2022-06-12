package com.jaweee.rabbitmq.topicexchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

/**
 * @author: me
 * @title: EmitLogTopic
 * @description:
 * @date: 2022/6/12 18:08
 */
public class EmitLogTopic {

    public static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try(Connection connection = factory.newConnection(); Channel channel = connection.createChannel()){
            channel.exchangeDeclare(EXCHANGE_NAME, "topic");


            String routingKey = args[0];
            String message = args[1];

            channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");
        }
    }
}
