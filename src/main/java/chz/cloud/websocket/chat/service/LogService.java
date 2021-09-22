package chz.cloud.websocket.chat.service;

import chz.cloud.websocket.chat.entities.LogPojo;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: chz
 * @Date: 2021/09/22/16:14
 * @Description:
 */
public interface LogService {
    List<LogPojo>selectPage(Integer page, Integer rows);
}
