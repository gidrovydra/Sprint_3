package courier;

import client.CourierApiClient;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;

public class StepsCourier {

    private final CourierApiClient apiClient = new CourierApiClient();

    @Step("Создать курьера")
    public ValidatableResponse createCourier(String courierLogin, String courierPassword, String courierFirstName) {
        return apiClient.createCourierAndReturnResponse(courierLogin,courierPassword,  courierFirstName)
                .then()
                .statusCode(SC_CREATED)
                .body("ok",equalTo(true));
    }

    @Step ("Удалить курьера")
    public void deleteCourier(String courierLogin, String courierPassword){
        apiClient.deleteCourierByLoginPasswordAndReturnResponse(courierLogin,courierPassword)
                .then()
                .statusCode(SC_OK)
                .body("ok", equalTo(true));
    }

    @Step("Проверка, создавался ли курьер заданными праметрами")
    public boolean loginCourierAndReturnBoolean(String courierLogin, String courierPassword) {

        if (apiClient.loginCourierWithLoginPasswordAndReturnResponse(courierLogin, courierPassword)
            .then()
            .extract()
            .statusCode() == SC_OK)
            return true;
        else
            return false;
}



}
