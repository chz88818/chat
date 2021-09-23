package chz.cloud.websocket.chat.tools;

import chz.cloud.websocket.chat.config.KafkaConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: chz
 * @Date: 2021/09/23/10:01
 * @Description:
 */

@Component
public class KafkaUtils {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private KafkaConfiguration configuration;

    public String sendMessage(Exception em){//String topic ,String key,String message){
        try {
            Writer writer=new StringWriter();
            //将上面的writer对象包装成一个PrintWriter对象
            PrintWriter pw =new PrintWriter(writer);
            //将错误信息打印到一个printwriter对象中
            em.printStackTrace(pw);
            kafkaTemplate.send("test_log", "user", writer.toString());
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
        return "success";
    }

}
