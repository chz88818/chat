package com.chz.rabbitmq.springbootorderrabbitmqconsumer.service.direct;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: chz
 * @Date: 2021/08/20/15:59
 * @Description:
 */
@Configuration
public class DirectRabbitMqConfiguration {
    //1.声明注册direct模式的交换机
    @Bean
    public DirectExchange directExchange(){
        return  new DirectExchange("direct_order_exchange",true,false);//交换机和持久化和自动删除
    }
    //2.声明队列sms.direct.queue  email.direct.queue   duanxin.direct.queue
    //注意bean不能同名

    @Bean
    public Queue smsQueue(){
        return new Queue("sms.direct.queue",true);
    }

    @Bean
    public Queue duanxinQueue(){
        return new Queue("duanxin.direct.queue",true);
    }

    @Bean
    public Queue emailQueue(){
        return new Queue("email.direct.queue",true);
    }
    //3.完成绑定关系(队列和交换机完成绑定关系)

    @Bean
    public Binding smsBinding(){
        return BindingBuilder.bind(smsQueue()).to(directExchange()).with("sms");
    }

    @Bean
    public Binding duanxinBinding(){
        return BindingBuilder.bind(duanxinQueue()).to(directExchange()).with("duanxin");
    }

    @Bean
    public Binding emailBinding(){
        return BindingBuilder.bind(emailQueue()).to(directExchange()).with("email");
    }
    //4.
}
