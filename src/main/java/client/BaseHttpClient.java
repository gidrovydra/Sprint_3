package client;

import static io.restassured.RestAssured.given;


import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

//базовый клиент - родитель
public class BaseHttpClient {

   // public final String baseUrl = "http://qa-scooter.praktikum-services.ru";
    private final String JSON = "application/json";

    //базовая спецификация запроса
    private RequestSpecification requestSpec = given()
            .baseUri("http://qa-scooter.praktikum-services.ru")
            .header("Content-Type", JSON);


    protected Response doGetRequest (String uri) {
        return requestSpec
                .get(uri);
    }

    protected Response doPostRequest (String uri, Object object) {
        return  requestSpec
                .and()
                .body(object)
                .when()
                .post(uri);
    }

    /*protected Response doDelRequest (String uri) {
        return  requestSpec
                .delete(uri);
    }*/

    protected Response doDelRequest (String nameParam, Long id, String uri) {
        return  requestSpec
                .body(Map.of(nameParam, id))
                .delete(uri, id);
    }

}
