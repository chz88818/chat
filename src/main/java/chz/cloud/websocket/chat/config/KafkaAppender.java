package chz.cloud.websocket.chat.config;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.ConsoleAppender;
import chz.cloud.websocket.chat.entities.SimpleLog;
import chz.cloud.websocket.chat.formatter.Formatter;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class KafkaAppender extends ConsoleAppender<ILoggingEvent> {


    private static final String bootstrapServers = "121.43.151.199:9092";
    //kafka生产者
    private KafkaTemplate kafkaTemplate;
    private SimpleLog simpleLog = new SimpleLog();

    @Override
    public void start() {
        super.start();
        Map<String, Object> props = new HashMap();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaTemplate = new KafkaTemplate(new DefaultKafkaProducerFactory(props));
        kafkaTemplate.send("test_log2", "user", "连接到Kafka。。。。。。。");// 先连接一遍，如果去掉可能报   Failed to update metadata after 60000 ms
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Override
    protected void append(ILoggingEvent eventObject) {
        long timeStamp = eventObject.getTimeStamp();
        Date date = new Date(timeStamp);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(date);
        String levStr = eventObject.getLevel().toString();
        String formattedMessage = eventObject.getFormattedMessage();
        if (ObjectUtils.isNotEmpty(eventObject.getThrowableProxy()) ) {
            StackTraceElementProxy[] stackTraceElementProxyArray = eventObject.getThrowableProxy().getStackTraceElementProxyArray();
            simpleLog.setStackTraceElementProxyArray(stackTraceElementProxyArray);
        }
        String threadName = eventObject.getThreadName();
        simpleLog.setThreadName(threadName);
        simpleLog.setDate(dateStr);
        simpleLog.setLevel(levStr);
        simpleLog.setMessage(formattedMessage);
        kafkaTemplate.send("test_log3", "user", JSON.toJSONString(simpleLog));//eventObject.getFormattedMessage()
        //kafkaTemplate.send("test_log1","user", "连接到Kafka。。。。。。。");// 先连接一遍，如果去掉可能报   Failed to update metadata after 60000 ms
    }
}
