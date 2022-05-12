package base;

import org.apache.commons.lang3.RandomStringUtils;

//в классе содержатся определенные значения полей для создания курьеров

public class Data {

    public String randomPart = RandomStringUtils.randomAlphabetic(3);
    public String randomLoginCourier = RandomStringUtils.randomAlphabetic(10);
    public String randomPasswordCourier = RandomStringUtils.randomAlphabetic(10);
    public String randomFirstNameCourier = RandomStringUtils.randomAlphabetic(10);
}
