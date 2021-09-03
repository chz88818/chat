package com.chz.rabbitmq.springbootorderrabbitmqconsumer.service;

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
@RabbitListener(queues = {"email.fanout.queue"})
@Service
public class FanoutEmailConsumer {
    @RabbitHandler
    public void
    receiveMessage(String message) {
        System.out.println("email fanout---接收到了订单信息是:->" + message);
    }

}
