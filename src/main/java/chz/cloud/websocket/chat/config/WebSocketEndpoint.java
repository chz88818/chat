package chz.cloud.websocket.chat.config;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: chz
 * @Date: 2021/08/18/10:08
 * @Description:
 */
@ServerEndpoint(value = "/websocket/{userId}")
@Component
public class WebSocketEndpoint {
    //与某个客户端的连接会话，需要通过它来给客户端发消息
    private Session session;

    /**
     * 建立连接成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) throws Exception {
        //把会话存储到连接池中
        SessionPool.sessions.put(userId, session);
    }


    /**
     * 关闭连接
     */
    @OnClose
    public void onClose(Session session) throws Exception {
        SessionPool.close(session.getId());
        session.close();
    }


    /**
     * 收到客户端消息后调用的方法
     *
     * @Param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) throws Exception {
        //如果是心跳检测的消息，则返回pong作为心跳回应
        if (message.equalsIgnoreCase("ping")) {
            try {
                Map<String, Object> params1 = new HashMap<String, Object>();
                params1.put("type", "pong");
                session.getBasicRemote().sendText(JSON.toJSONString(params1));
                System.out.println("应答客户端的消息:" + JSON.toJSONString(params1));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else {
            Map<String, Object> params = JSON.parseObject(message, new HashMap<String, Object>().getClass());
            SessionPool.sendMessage(params);
        }
    }
}
