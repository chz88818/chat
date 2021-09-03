package chz.cloud.websocket.chat.config;

import org.springframework.amqp.core.*;
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
public class TTLRabbitMqConfiguration {

    //声明注册direct模式的交换机
    @Bean
    public DirectExchange ttldirectExchange() {
        return new DirectExchange("ttl_direct_exchange", true, false);//交换机和持久化和自动删除
    }

    //队列的过期时间
    @Bean
    public Queue directttlQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", 5000);//这里一定是int类型
        args.put("x-dead-letter-exchange","dead_direct_exchange");
        args.put("x-dead-letter-routing-key","dead");//fanout模式不需要配置
        return new Queue("ttl.direct.queue", true, false, false, args);
    }


    //队列的过期时间
    @Bean
    public Queue directttlMessageQueue() {

        return new Queue("ttl.message.direct.queue", true);
    }


    @Bean
    public Binding ttlBinding() {
        return BindingBuilder.bind(directttlQueue()).to(ttldirectExchange()).with("ttl");
    }

    @Bean
    public Binding ttlmsgBinding() {
        return BindingBuilder.bind(directttlMessageQueue()).to(ttldirectExchange()).with("ttlmessage");
    }

}
