package order;

import client.OrderApiClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.notNullValue;

public class GetOrdersListTest {

    private OrderApiClient apiClient = new OrderApiClient();


    @Test
    //проверяем, что получаем список заказов по классу и трек-номеру заказа в списке
    public void checkOrderList() {

        apiClient.getOrdersListAndReturnResponse()
                .then().assertThat()
                .statusCode(200)
                .body("orders", isA(ArrayList.class))
                .body("orders.track", notNullValue());
    }
}
