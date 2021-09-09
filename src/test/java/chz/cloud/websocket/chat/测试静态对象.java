package chz.cloud.websocket.chat;

import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: chz
 * @Date: 2021/09/09/17:11
 * @Description:
 */
@Component
public class 测试静态对象 {
    private static 测试类 测试类;
    public static void main(String[] args) {
        测试类 测试类=new 测试类();
        测试静态对象.测试类=测试类;
//        System.out.println(测试类.toString());
//        System.out.println(测试类.toString());
    }

}
