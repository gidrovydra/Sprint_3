package order;

import model.Order;
import client.OrderApiClient;

import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.List;

import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderDifferentColorParametrizedTest {

    private  static StepsOrder steps = new StepsOrder();
    private OrderApiClient apiClient = new OrderApiClient();
    private Order order = new Order();
    private  final List<String> color;
    private int expected = HttpStatus.SC_CREATED;

    public CreateOrderDifferentColorParametrizedTest(List<String> color) {
        this.color = color;
    }


    @Parameterized.Parameters
    public static List<List<String>> getColor(){
        return List.of(
                List.of(""),
                List.of("GREY"),
                List.of("BLACK"),
                List.of("GREY", "BLACK")
        );
    }

    @Test
    //проверяем создание заказа, выбираем значения параметра Цвет
    public void checkCreateOrderWithDifferentColor() {

        order  = steps.createExampleOrder(color);

        apiClient.createOrderAndReturnResponse(order)
                .then()
                .assertThat()
                .statusCode(expected)
                .body("track", notNullValue());
    }
}
