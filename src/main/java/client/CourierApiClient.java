package client;

import model.Courier;

import io.restassured.response.Response;

//клиент для тестирования ручек Курьер (наследник базового)
public class CourierApiClient extends BaseHttpClient {

    public String handleLoginCourier = "/api/v1/courier/login";
    public String handleCreateCourier = "/api/v1/courier";
    public String handleDeleteCourier = "/api/v1/courier/{id}";

    public String keyForDelReqInJSON = "id";

    //базовый метод: создаем курьера с заданными параметрами и возвращаем ответ
    public Response createCourierAndReturnResponse(String courierLogin, String courierPassword, String courierFirstName ) {
        Courier courier = new Courier(courierLogin, courierPassword, courierFirstName);
        return doPostRequest(handleCreateCourier,courier);
    }


    //базовый метод: создаем курьера с без параметра courierFirstName и возвращаем ответ
    public Response createCourierAndReturnResponse(String courierLogin, String courierPassword ) {
        Courier courier = new Courier(courierLogin, courierPassword);
        return doPostRequest(handleCreateCourier,courier);
    }

    //залогинить курьера и получить id
    public long loginCourierWithLoginPasswordAndReturnId(String courierLogin, String courierPassword) {
        Courier courier = new Courier(courierLogin, courierPassword);
        return doPostRequest(handleLoginCourier,courier).getBody().as(Courier.class).getId();
    }

    //залогинить курьера и получить ответ
    public  Response loginCourierWithLoginPasswordAndReturnResponse(String courierLogin, String courierPassword) {
        Courier courier = new Courier(courierLogin, courierPassword);
        return doPostRequest(handleLoginCourier,courier);
    }

    //удалить курьера и получить ответ
    public Response deleteCourierByLoginPasswordAndReturnResponse(String courierLogin, String courierPassword) {
        return doDelRequest(keyForDelReqInJSON, loginCourierWithLoginPasswordAndReturnId(courierLogin,courierPassword),handleDeleteCourier);
    }


    //удалить курьера по id
    public Response  deleteCourierByIdAndReturnResponse(Long id) {
        return doDelRequest(keyForDelReqInJSON, id, handleDeleteCourier);
    }

}
