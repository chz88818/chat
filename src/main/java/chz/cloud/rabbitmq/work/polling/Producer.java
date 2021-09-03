package chz.cloud.rabbitmq.work.polling;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.commons.lang3.ObjectUtils;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: chz
 * @Date: 2021/08/20/13:49
 * @Description:
 */
public class Producer {
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
            //String queueName = "queue1";
            /**
             * @Params1 队列的名称
             * @Params2 是否持久化  durable=false 所谓持久化消息是否存盘，如果false 非持久化 true 是持久化 非持久化会存盘吗?会存盘，但是会随服务器重启丢失
             * @Params3 排他性 是否独占队列
             * @Params4 是否自动删除，随着最后一个消费者消费完毕后是否把队列自动删除
             * @Params5 携带一些附加参数
             */
            // channel.queueDeclare(queueName, false, false, false, null);
            //5.准备消息内容
//            String message = "Hello 卯男神  order";

            //准备交换机
//            String exchangeName="all_exchange";
//            String routeKey="course";
//            String type="direct";
            //6.发送消息给队列queue
            //@Params1:交换机  @Params2:队列路由key  @params3 消息的状态控制    @Params4 消息主题
//声明交换机
            //     channel.exchangeDeclare(exchangeName,type,true);
//声明队列
//            channel.queueDeclare("queue5",true,false,false,null);
//            channel.queueDeclare("queue6",true,false,false,null);
//            channel.queueDeclare("queue7",true,false,false,null);

//绑定队列和交换机的关系

//            channel.queueBind("queue5",exchangeName,"order");
//            channel.queueBind("queue6",exchangeName,"order");
//            channel.queueBind("queue7",exchangeName,"course");

            //么有指定的交换机，但是一定会存在一个默认的交换机
            for (int i = 1; i <= 20; i++) {
                String message = "Hello " + i;
                channel.basicPublish("", "queue1", null, message.getBytes());
            }
            System.out.println("消息发送成功");
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
