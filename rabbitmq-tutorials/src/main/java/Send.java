import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

/**
 * @description: TODO
 * @author: jiawei
 * @date: 2022/6/10 07:12
 * @github: https://github.com/jaweee
 * @version: 1.0
 */
public class Send {

    // the queue as the post box
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try(Connection connection = factory.newConnection();Channel channel = connection.createChannel();){

            channel.queueDeclare(QUEUE_NAME, false,false,false,null);
            String message = "hello world";
            channel.basicPublish("", QUEUE_NAME,null,message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] sent ' " + message + " ' ");

        }
    }
}
