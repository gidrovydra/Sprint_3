package courier;

import client.CourierApiClient;

import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;

import static org.hamcrest.Matchers.equalTo;

public class StepsCourier {

    private final CourierApiClient apiClient = new CourierApiClient();

    //шаг "Создать курьера"
    public ValidatableResponse createCourier(String courierLogin, String courierPassword, String courierFirstName) {
        return apiClient.createCourierAndReturnResponse(courierLogin,courierPassword,  courierFirstName)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("ok",equalTo(true));
    }

    //шаг "Удалить курьера"
    public void deleteCourier(String courierLogin, String courierPassword){
        apiClient.deleteCourierByLoginPasswordAndReturnResponse(courierLogin,courierPassword)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("ok", equalTo(true));
    }
}