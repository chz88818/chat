package chz.cloud.websocket.chat;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: chz
 * @Date: 2021/09/09/19:05
 * @Description:
 */
public class 策士 {

    private static String  string;

    public void setChatService(String  string) {
        策士.string = string;
    }


    public String get(){
        return 策士.string;
    }

    public String get策士(){
        return 策士.string;
    }
}
class A{
    public static void main(String[] args) {
        策士 a=new 策士();
        a.setChatService("123");
        System.out.println(a.get());
        System.out.println(a.get策士());
        策士 b=new 策士();
        System.out.println(b.get());
        System.out.println(b.get策士());
    }
}