package chz.cloud.websocket.chat.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: chz
 * @Date: 2021/08/18/10:25
 * @Description:
 */
@Component
public class SessionPool {
    public static Map<String, Session> sessions = new ConcurrentHashMap<>();

    public static void close(String sessionId) throws Exception {
        for (String userId : SessionPool.sessions.keySet()) {
            Session session = SessionPool.sessions.get(userId);
            if (session.getId().equals(sessionId)) {
                sessions.remove(userId);
                break;
            }
        }
//        Session session=sessions.get(sessionId);
//        if (session!=null){
//            sessions.get(sessionId).close();
//        }
    }

    public static void sendMessage(String sessionId, String message) {
        sessions.get(sessionId).getAsyncRemote().sendText(message);
    }

    //群发
    public static void sendMessage(String message) {
        for (String sessionId : SessionPool.sessions.keySet()) {
            SessionPool.sessions.get(sessionId).getAsyncRemote().sendText(message);
        }
    }

    //私发
    public static void sendMessage(Map<String, Object> params) throws Exception {
        String toUserId = params.get("toUserId").toString();
        String msg = params.get("msg").toString();
        String fromUserId = params.get("fromUserId").toString();
        fromUserId= URLDecoder.decode(fromUserId, "UTF-8");
        msg = "来自[" + fromUserId + "]的消息:" + msg;
        if (StringUtils.isNotEmpty(toUserId)) {
            Session session = sessions.get(toUserId);
            if (session != null) {
                session.getAsyncRemote().sendText(msg);
            }
        } else {
            sendMessage(msg);
        }
    }
}
