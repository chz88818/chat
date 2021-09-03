package chz.cloud.websocket.chat.service;

import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: chz
 * @Date: 2021/08/20/15:30
 * @Description:
 */
@Service
public class OrderService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 模拟用户下单
     */
    public void makeOrder(String userId, String productId, int num) {
        //1.根据商品ID查询库存是否充足
        //2.保存订单
        String orderId = UUID.randomUUID().toString();
        System.out.println("订单生成成功:" + orderId);
        //3.通过MQ来完成消息分发
        //参数1:交换机，参数2：路由Key/queue队列名称，参数3：消息内容
        String exchangeName = "fanout_order_exchange";
        String routingKey = "";
        rabbitTemplate.convertAndSend(exchangeName, routingKey, orderId);

    }


    /**
     * 模拟用户下单
     */
    public void makeOrderDirect(String userId, String productId, int num) {
        //1.根据商品ID查询库存是否充足
        //2.保存订单
        String orderId = UUID.randomUUID().toString();
        System.out.println("订单生成成功:" + orderId);
        //3.通过MQ来完成消息分发
        //参数1:交换机，参数2：路由Key/queue队列名称，参数3：消息内容
        String exchangeName = "direct_order_exchange";
        rabbitTemplate.convertAndSend(exchangeName, "email", orderId);
        rabbitTemplate.convertAndSend(exchangeName, "duanxin", orderId);
    }


    /**
     * 模拟用户下单
     */
    public void makeOrderTopic(String userId, String productId, int num) {
        //1.根据商品ID查询库存是否充足
        //2.保存订单
        String orderId = UUID.randomUUID().toString();
        System.out.println("订单生成成功:" + orderId);
        //3.通过MQ来完成消息分发
        //参数1:交换机，参数2：路由Key/queue队列名称，参数3：消息内容
        String exchangeName = "topic_order_exchange";
        String routekey = "com.email.duanxin";
        rabbitTemplate.convertAndSend(exchangeName, routekey, orderId);
    }


    /**
     * 模拟用户下单
     */
    public void makeOrderTtl(String userId, String productId, int num) {
        //1.根据商品ID查询库存是否充足
        //2.保存订单
        String orderId = UUID.randomUUID().toString();
        System.out.println("订单生成成功:" + orderId);
        //3.通过MQ来完成消息分发
        //参数1:交换机，参数2：路由Key/queue队列名称，参数3：消息内容
        String exchangeName = "ttl_direct_exchange";
        String routekey = "ttl";
        rabbitTemplate.convertAndSend(exchangeName, routekey, orderId);
    }


    /**
     * 模拟用户下单
     */
    public void makeOrderTtlMessage(String userId, String productId, int num) {
        //1.根据商品ID查询库存是否充足
        //2.保存订单
        String orderId = UUID.randomUUID().toString();
        System.out.println("订单生成成功:" + orderId);
        //3.通过MQ来完成消息分发
        //参数1:交换机，参数2：路由Key/queue队列名称，参数3：消息内容
        String exchangeName = "ttl_direct_exchange";
        String routekey = "ttlmessage";
        //给消息设置过期时间
        MessagePostProcessor messagePostProcessor = (message) -> {
            message.getMessageProperties().setExpiration("5000");
            message.getMessageProperties().setContentEncoding("UTF-8");
            return message;
        };
        rabbitTemplate.convertAndSend(exchangeName, routekey, orderId, messagePostProcessor);
    }


}
