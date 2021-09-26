package chz.cloud.websocket.chat.config;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import chz.cloud.websocket.chat.formatter.Formatter;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: chz
 * @Date: 2021/09/26/9:13
 * @Description:
 */
public class KafkaAppender extends AppenderBase<ILoggingEvent> {



    private static final String bootstrapServers="121.43.151.199:9092";
    //kafka生产者
    private KafkaTemplate kafkaTemplate;
    @Override
    public void start() {
        super.start();
        Map<String, Object> props = new HashMap();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("retries", 0);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaTemplate = new KafkaTemplate(new DefaultKafkaProducerFactory(props));
        kafkaTemplate.send("test_log","user", "连接到Kafka。。。。。。。");// 先连接一遍，如果去掉可能报   Failed to update metadata after 60000 ms
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Override
    protected void append(ILoggingEvent eventObject) {
        kafkaTemplate.send("test_log", "user",eventObject.getFormattedMessage());
    }



}
