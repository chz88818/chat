package chz.cloud.websocket.chat.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: chz
 * @Date: 2021/08/24/16:52
 * @Description:
 */
@Configuration
public class DeadRabbitMqConfiguration {

    //声明注册direct模式的交换机
    @Bean
    public DirectExchange deadDirectExchange() {
        return new DirectExchange("dead_direct_exchange", true, false);//交换机和持久化和自动删除
    }

    //队列的过期时间
    @Bean
    public Queue deadQueue() {
        return new Queue("dead.direct.queue", true);
    }

    @Bean
    public Binding deadBinding() {
        return BindingBuilder.bind(deadQueue()).to(deadDirectExchange()).with("dead");
    }

}
