package courier;

import client.CourierApiClient;
import base.Data;

import io.qameta.allure.junit4.DisplayName;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;


public class CreateCourierTests {

    private CourierApiClient apiClient = new CourierApiClient();
    private Data data =  new Data();
    private StepsCourier steps = new StepsCourier();

@After
public void cleanUp() {
    if (steps.loginCourierAndReturnBoolean(data.randomLoginCourier , data.randomPasswordCourier)) {
       steps.deleteCourier(data.randomLoginCourier,data.randomPasswordCourier);
   }
}

    @Test
    @DisplayName("Создать курьера со всеми параметрами")
    public void checkCreateCourierPositive() {

        apiClient.createCourierAndReturnResponse(data.randomLoginCourier,data.randomPasswordCourier, data.randomFirstNameCourier)
                .then().assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .body("ok",equalTo(true));
    }


    @Test
    @DisplayName("Создание дублирующего курьера")
    public void checkCreateDuplicateCourier() {

        steps.createCourier(data.randomLoginCourier,data.randomPasswordCourier, data.randomFirstNameCourier);
        apiClient.createCourierAndReturnResponse(data.randomLoginCourier,data.randomPasswordCourier, data.randomFirstNameCourier)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CONFLICT)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @DisplayName("Создание курьера без параметра FirstName")
    public void checkCreateCourierWithoutFirstName(){
        apiClient.createCourierAndReturnResponse(data.randomLoginCourier,data.randomPasswordCourier)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Создание курьера без параметра Login")
    public void checkCreateCourierWithoutLogin(){
        apiClient.createCourierAndReturnResponse(null, data.randomPasswordCourier,data.randomFirstNameCourier)
                .then().assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание курьера без параметра Password")
    public void checkCreateCourierWithoutPassword(){
        apiClient.createCourierAndReturnResponse(data.randomLoginCourier,null, data.randomFirstNameCourier)
                .then().assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
}
