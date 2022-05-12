package client;

import model.Order;

import io.restassured.response.Response;

//клиент для тестирования ручек Заказа (наследник базового)
public class OrderApiClient extends BaseHttpClient {

    public String handleListOrders = "/api/v1/orders";
    public String handleCreateOrder = "/api/v1/orders";


    public Response getOrdersListAndReturnResponse() {
        return (doGetRequest(handleListOrders));
    }

    public Response createOrderAndReturnResponse(Order order) {
        return doPostRequest(handleCreateOrder, order);
    }
}