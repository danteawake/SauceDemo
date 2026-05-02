package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class LoginTest extends BaseTest {

    @Test(groups = {"smoke", "regression"},
            testName = "Успешный вход в систему",
            description = "Успешный вход в систему")
    public void checkLoginWithPositiveValue() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals("Products", productsPage.getTitle());
    }

    @Test(enabled = false,
            groups = {"regression"},
            testName = "Проверка ошибки при пустом пароле",
            description = "Проверка ошибки при пустом пароле")
    public void checkLoginWithEmptyPassword() {
        loginPage.open();
        loginPage.login("standard_user", "");
        assertEquals("Epic sadface: Password is required", loginPage.getErrorMessage());
    }

    @Test(enabled = false,
            groups = {"regression"},
            testName = "Проверка ошибки при пустом логине",
            description = "Проверка ошибки при пустом логине")
    public void checkLoginWithEmptyUser() {
        loginPage.open();
        loginPage.login("", "secret_sauce");
        assertEquals("Epic sadface: Username is required", loginPage.getErrorMessage());
    }

    @Test(enabled = false,
            groups = {"regression"},
            testName = "Проверка ошибки при неверных данных",
            description = "Проверка ошибки при неверных данных")
    public void checkLoginWithNegativeValue() {
        loginPage.open();
        loginPage.login("test", "test");
        assertEquals("Epic sadface: Username and password do not match any user in this service",
                loginPage.getErrorMessage());
    }

    //DataProvider
    @Test(
            groups = {"regression"},
            dataProvider = "negativeRegressLoginData",
            description = "Негативные проверки логина"
    )
    public void checkNegativeLoginDP(String user, String password, String expectedError) {
        loginPage.open();
        loginPage.login(user, password);
        assertEquals(expectedError, loginPage.getErrorMessage());
    }

    @DataProvider(name = "negativeRegressLoginData")
    public Object[][] negativeData() {
        return new Object[][]{
                {"standard_user", "", "Epic sadface: Pasword is required"},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"test", "test", "Epic sadface: Username and password do not match any user in this service"}
        };
    }
}



