package courier;

import client.CourierApiClient;
import base.Data;

import org.apache.http.HttpStatus;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;


public class CreateCourierTests {

    private CourierApiClient apiClient = new CourierApiClient();
    private Data data =  new Data();
    private StepsCourier steps = new StepsCourier();


    @Test
    //проверяем можно ли создать курьера со всеми параметрами
    public void checkCreateCourierPositive() {
        apiClient.createCourierAndReturnResponse(data.randomLoginCourier,data.randomPasswordCourier, data.randomFirstNameCourier)
                .then().assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .body("ok",equalTo(true));

        steps.deleteCourier(data.randomLoginCourier,data.randomPasswordCourier);
    }


    @Test
    //проверка создания дублирующего курьера
    public void checkCreateDuplicateCourier() {
        steps.createCourier(data.LOGIN_COURIER,data.PASSWORD_COURIER, data.FIRST_NAME_COURIER);

        apiClient.createCourierAndReturnResponse(data.LOGIN_COURIER,data.PASSWORD_COURIER, data.FIRST_NAME_COURIER)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CONFLICT)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));

        steps.deleteCourier(data.LOGIN_COURIER,data.PASSWORD_COURIER);
    }

    @Test
    //проверка создания курьера без Имени
    public void checkCreateCourierWithoutFirstName(){
        apiClient.createCourierAndReturnResponse(data.randomLoginCourier,data.randomPasswordCourier)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .body("ok", equalTo(true));

        steps.deleteCourier(data.randomLoginCourier,data.randomPasswordCourier);
    }

    @Test
    //проверка создания курьера без Логина
    public void checkCreateCourierWithoutLogin(){
        apiClient.createCourierAndReturnResponse(null, data.PASSWORD_COURIER, data.FIRST_NAME_COURIER)
                .then().assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    //проверка создания курьера без Пароля
    public void checkCreateCourierWithoutPassword(){
        apiClient.createCourierAndReturnResponse(data.randomLoginCourier,null, data.randomFirstNameCourier)
                .then().assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

}
