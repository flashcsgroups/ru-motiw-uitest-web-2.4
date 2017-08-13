package ru.motiw.data.dataproviders;

import org.testng.annotations.DataProvider;
import ru.motiw.data.BaseTest;

/**
 * Данные для пользовательской авторизации
 */
public abstract class UserAuthentication extends BaseTest {

    /**
     * Инициализация входных данных для Логин и Пароль
     */
    @DataProvider(name = "verifyFailAuthorization")
    public Object[][] firstNotSuccessfulAuthorizationDataProvider() {
        return new Object[][]{
                {"fail", "admin"},
                {"admin", "fail"},
                {"fail", "fail"},
                {"admin", ""}
        };
    }

    @DataProvider(name = "secondVerifyFailAuthorization")
    public Object[][] secondNotSuccessfulAuthorizationDataProvider() {
        return new Object[][]{
                {"", "admin"},
                {"", ""}
        };
    }

    /**
     * Инициализация входных данных для Логин и Пароль
     */
    @DataProvider(name = "verifyFailAuthorizationWeb")
    public static Object[][] notSuccessfulAuthorizationDataProvider() {
        return new Object[][]{
                {"fail", "admin"},
                {"admin", "fail"},
                {"fail", "fail"},
                {"admin", ""},
                {"", "admin"},
                {"", ""}
        };
    }
}
