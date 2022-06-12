package com.jaweee.rabbitmq.topicexchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

/**
 * @author: me
 * @title: ReceiveLogTopic
 * @description:
 * @date: 2022/6/12 18:12
 */
public class ReceiveLogTopic {

    public static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        String queueName = channel.queueDeclare().getQueue();
        if (args.length < 1){
            System.out.println("no binding");
            System.exit(1);
        }

        for (String item : args){
            channel.queueBind(queueName, EXCHANGE_NAME, item);
        }

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = ((consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" +
                    delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
        });

        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
    }
}
