package chz.cloud.websocket.chat.formatter;

import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: chz
 * @Date: 2021/09/26/9:07
 * @Description:
 */


public class MessageFormatter implements Formatter {

    @Override
    public String format(ILoggingEvent event) {
        return event.getFormattedMessage();
    }

}

