package chz.cloud.websocket.chat.controller;

import chz.cloud.websocket.chat.entities.LogPojo;
import chz.cloud.websocket.chat.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: chz
 * @Date: 2021/09/22/16:26
 * @Description:
 */
@RestController
public class LogController {
    @Autowired
    private LogService logService;

    @RequestMapping("/")
    public List<LogPojo> show(Integer page, Integer rows) {
        return logService.selectPage(page, rows);
    }
}
