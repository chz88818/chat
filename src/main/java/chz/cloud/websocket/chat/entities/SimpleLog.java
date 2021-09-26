package chz.cloud.websocket.chat.entities;

import ch.qos.logback.classic.spi.StackTraceElementProxy;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: chz
 * @Date: 2021/09/26/14:08
 * @Description:
 */
@Data
public class SimpleLog {
    private String date;
    private String level;
    private String message;
    private StackTraceElementProxy[] stackTraceElementProxyArray;
    private String threadName;
}
