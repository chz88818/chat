package com.chz.rabbitmq.springbootorderrabbitmqconsumer.service.topic;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: chz
 * @Date: 2021/08/20/17:53
 * @Description:
 */
@RabbitListener(bindings = @QueueBinding(value = @Queue(value = "sms.topic.queue", durable = "true", autoDelete = "false"),
        exchange = @Exchange(value = "topic_order_exchange", type = ExchangeTypes.TOPIC), key = "com.#"))
@Service
public class TopicSmsConsumer {

    @RabbitHandler
    public void
    receiveMessage(String message) {
        System.out.println("sms direct---接收到了订单信息是:->" + message);
    }

}
