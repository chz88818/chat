package chz.cloud.websocket.chat.controller;

import chz.cloud.websocket.chat.tools.KafkaUtils;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: chz
 * @Date: 2021/09/23/10:04
 * @Description:
 */
@RestController
public class KafkaTestController {
    @Autowired
    private KafkaUtils kafkaUtils;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @GetMapping("/kafka")
    public String sendMessageToKafka() {
        String s="无";
  //      try{
            int a=1/0;
//        }catch (Exception e) {
//            // String s = kafkaUtils.sendMessage();//("test_log", "user", "卯升超");
//             s = kafkaUtils.sendMessage(e);
//        }
        return s;
    }
}
