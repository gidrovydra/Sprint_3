package order;

import io.qameta.allure.junit4.DisplayName;
import model.Order;
import client.OrderApiClient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.List;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderDifferentColorParametrizedTest {

    private  static StepsOrder steps = new StepsOrder();
    private OrderApiClient apiClient = new OrderApiClient();
    private Order order = new Order();
    private  final List<String> color;


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
    @DisplayName("Параметризованная проверка создания корректого заказа с разными значениями параметра COLOR")
    public void checkCreateOrderWithDifferentColor() {

        order = steps.createExampleOrder(color);

        apiClient.createOrderAndReturnResponse(order)
                .then()
                .assertThat()
                .statusCode(SC_CREATED)
                .body("track", notNullValue());
      }
}
