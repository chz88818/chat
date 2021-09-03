package com.chz.rabbitmq.springbootorderrabbitmqconsumer.service.direct;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: chz
 * @Date: 2021/08/20/17:53
 * @Description:
 */
@RabbitListener(queues = {"email.direct.queue"})
@Service
public class DirectEmailConsumer {
    @RabbitHandler
    public void
    receiveMessage(String message) {
        System.out.println("email direct---接收到了订单信息是:->" + message);
    }

}
