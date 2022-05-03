package base;

import org.apache.commons.lang3.RandomStringUtils;

//в классе содержатся определенные значения полей для создания курьеров

public class Data {

    public final String LOGIN_COURIER = "MyLogin38781";
    public final String PASSWORD_COURIER = "MyPassword38781";
    public final String FIRST_NAME_COURIER = "MyName38781";

    public String randomPart = RandomStringUtils.randomAlphabetic(3);
    public String randomLoginCourier = RandomStringUtils.randomAlphabetic(10);
    public String randomPasswordCourier = RandomStringUtils.randomAlphabetic(10);
    public String randomFirstNameCourier = RandomStringUtils.randomAlphabetic(10);
}
