package client;

import static io.restassured.RestAssured.given;


import io.restassured.response.Response;

import java.util.Map;

//базовый клиент - родитель
public class BaseHttpClient {

    public final String baseUrl = "http://qa-scooter.praktikum-services.ru";
    private final String JSON = "application/json";

    protected Response doGetRequest (String uri) {
        return given().header("Content-Type", JSON).get(uri);
    }

    protected Response doPostRequest (String uri, Object object) {
        return given()
                .header("Content-Type", JSON)
                .and()
                .body(object)
                .when()
                .post(uri);
    }

    protected Response doDelRequest (String uri) {

        return given()
                .header("Content-Type", JSON)
                .delete(uri);
    }

    protected Response doDelRequest (String nameParam, Long id, String uri) {

        return given()
                .header("Content-Type", JSON)
                .body(Map.of(nameParam, id))
                .delete(uri, id);
    }
}
