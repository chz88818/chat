package chz.cloud.rabbitmq.simple;

import com.rabbitmq.client.*;
import org.apache.commons.lang3.ObjectUtils;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: chz
 * @Date: 2021/08/19/10:27
 * @Description:
 */
public class Consumer {
    public static void main(String[] args) {
        //1.创建连接工程
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("121.43.151.199");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setVirtualHost("/");

        Connection connection = null;
        Channel channel = null;
        try {
            //2.创建连接Connection
            connection = connectionFactory.newConnection("生产者");
            //3.通过连接获取通道Channel
            channel = connection.createChannel();
            //4.通过创建交换机声明队列，绑定关系，路由key，发送消息和接收消息
//            channel.basicConsume("queue1", true, new DeliverCallback() {
//                @Override
//                public void handle(String s, Delivery delivery) throws IOException {
//                    System.out.println("收到消息是:" + new String(delivery.getBody(), "UTF-8"));
//                }
//            }, new CancelCallback() {
//                @Override
//                public void handle(String s) throws IOException {
//                    System.out.println("接收消息失败了");
//                }
//            });
            channel.basicConsume("queue1", true, (s, delivery) -> System.out.println("收到消息是:" + new String(delivery.getBody(), "UTF-8")), s -> System.out.println("接收消息失败了"));
            System.out.println("开始接受消息");
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //7.关闭通道
            if (ObjectUtils.isNotEmpty(channel) && channel.isOpen()) {
                try {
                    channel.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //8.关闭连接
            if (ObjectUtils.isNotEmpty(connection) && connection.isOpen()) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
