package chz.cloud.websocket.chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.PrintStream;

@SpringBootApplication
public class ChatApplication {

    public static void main(String[] args) {
        Logger logger= LoggerFactory.getLogger(ChatApplication.class);
        logger.error("自己写的ERROR");
        logger.info("自己写的INFO");
        SpringApplication.run(ChatApplication.class, args);
    }

}
