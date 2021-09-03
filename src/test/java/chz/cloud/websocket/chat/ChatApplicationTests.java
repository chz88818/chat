package chz.cloud.websocket.chat;

import chz.cloud.websocket.chat.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ChatApplicationTests {

    @Autowired
    private OrderService orderService;

    @Test
    public void contextLoads() {
        orderService.makeOrder("1", "1", 12);
    }

    @Test
    public void testOrderDirect() {
        orderService.makeOrderDirect("1", "1", 12);
    }

    @Test
    public void testTopicDirect() {
        orderService.makeOrderTopic("1", "1", 12);
    }

    @Test
    public void testTtlDirect() {
        for (int i = 0; i < 20; i++) {
            orderService.makeOrderTtl("1", "1", 12);
        }
    }

    @Test
    public void testTtlDirectmessage() {
        orderService.makeOrderTtlMessage("1", "1", 12);
    }
}
