package order;

import client.OrderApiClient;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import java.util.ArrayList;

import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.notNullValue;
import static org.apache.http.HttpStatus.SC_OK;

public class GetOrdersListTest {

    private OrderApiClient apiClient = new OrderApiClient();


    @Test
    @DisplayName("Проверка, что в ответе получаем список заказов")
    public void checkOrderList() {

        apiClient.getOrdersListAndReturnResponse()
                .then()
                .assertThat()
                .statusCode(SC_OK)
                .body("orders", isA(ArrayList.class))
                .body("orders.track", notNullValue());
    }
}
