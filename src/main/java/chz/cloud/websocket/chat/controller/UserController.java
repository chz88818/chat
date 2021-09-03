package chz.cloud.websocket.chat.controller;

import chz.cloud.websocket.chat.config.SessionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: chz
 * @Date: 2021/08/23/16:00
 * @Description:
 */
@RestController
public class UserController {

    @Autowired
    private SessionPool sessionPool;

    @PostMapping("/getUserList")
    public List<String> getUserList(@RequestParam(value = "getUser")String getUser){
        List<String> userList=new ArrayList<>();
        for (String user : SessionPool.sessions.keySet()) {
            userList.add(user);
        }
        return userList;
    }

}
