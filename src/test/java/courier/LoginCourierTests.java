package courier;

import base.Data;
import client.CourierApiClient;

import io.qameta.allure.junit4.DisplayName;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class LoginCourierTests {

    private CourierApiClient apiClient = new CourierApiClient();
    private Data data =  new Data();
    private StepsCourier steps = new StepsCourier();


    @Before
    //создаем курьера перед каждым тестом
    public void setUp() {
        steps.createCourier(data.randomLoginCourier, data.randomPasswordCourier, data.randomFirstNameCourier);
    }

    @After
    //удаляем курьера после каждого теста
    public void cleanUp() {
        steps.deleteCourier(data.randomLoginCourier, data.randomPasswordCourier);
    }

    @Test
    @DisplayName("Корректный логин курьера в системе")
    public void checkAuthorization(){
         apiClient.loginCourierWithLoginPasswordAndReturnResponse(data.randomLoginCourier, data.randomPasswordCourier)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("id",notNullValue());
    }

    @Test
    @DisplayName("Логин курьера без параметра Login")
    public void checkAuthorizationWithoutLogin(){
        apiClient.loginCourierWithLoginPasswordAndReturnResponse("", data.randomPasswordCourier)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Логин курьера без параметра Password")
    public void checkAuthorizationWithoutPassword(){
        apiClient.loginCourierWithLoginPasswordAndReturnResponse( data.randomLoginCourier, "")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Логин курьера с некоррекктным значением Login")
    public void checkAuthorizationWithIncorrectLogin(){
        apiClient.loginCourierWithLoginPasswordAndReturnResponse(data.randomLoginCourier+ data.randomPart, data.randomPasswordCourier)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Логин курьера с некоррекктным значением Password")
    public void checkAuthorizationWithIncorrectPassword(){
        apiClient.loginCourierWithLoginPasswordAndReturnResponse(data.randomLoginCourier, data.randomPasswordCourier + data.randomPart)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .body("message", equalTo("Учетная запись не найдена"));
    }
}
