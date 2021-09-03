package chz.cloud.rabbitmq.work.polling;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Delivery;
import org.apache.commons.lang3.ObjectUtils;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: chz
 * @Date: 2021/08/20/13:49
 * @Description:
 */
public class Work2 {
    private static Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //1.创建连接工程
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost("121.43.151.199");
            connectionFactory.setPort(5672);
            connectionFactory.setUsername("admin");
            connectionFactory.setPassword("admin");
            connectionFactory.setVirtualHost("/");

            //获取队列名
            final String queueName = Thread.currentThread().getName();
            Connection connection = null;
            Channel channel = null;
            try {
                //2.创建连接Connection
                connection = connectionFactory.newConnection("消费者-Work1");
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
                //定义接收消息的回调
                Channel finalChannel = channel;
                finalChannel.basicQos(1);
                finalChannel.basicConsume("queue1", true, (String s, Delivery delivery) ->
                {
                    try {
                        System.out.println("Work2-" + "收到消息是:" + new String(delivery.getBody(), "UTF-8"));
                        Thread.sleep(200);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }, s -> {
                });
                //System.out.println(queueName+"接收完成");
                System.out.println(queueName + "Work2-开始接受消息");
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
    };

    public static void main(String[] args) {
        //启动3个线程去执行
        new Thread(runnable, "queue1").start();
//        new Thread(runnable, "queue2").start();
//        new Thread(runnable, "queue3").start();
//        new Thread(runnable, "queue4").start();
    }
}
