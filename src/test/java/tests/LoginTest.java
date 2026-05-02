package tests;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class LoginTest extends BaseTest {

    @Test(groups = {"smoke", "regression"}, description = "Успешный вход в систему")
    public void checkLoginWithPositiveValue() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals("Products", productsPage.getTitle());
    }

    @Test(groups = {"regression"}, description = "Проверка ошибки при пустом пароле")
    public void checkLoginWithEmptyPassword() {
        loginPage.open();
        loginPage.login("standard_user", "");
        assertEquals("Epic sadface: Password is required", loginPage.getErrorMessage());
    }

    @Test(groups = {"regression"}, description = "Проверка ошибки при пустом логине")
    public void checkLoginWithEmptyUser() {
        loginPage.open();
        loginPage.login("", "secret_sauce");
        assertEquals("Epic sadface: Username is required", loginPage.getErrorMessage());
    }

    @Test(groups = {"regression"}, description = "Проверка ошибки при неверных данных")
    public void checkLoginWithNegativeValue() {
        loginPage.open();
        loginPage.login("test", "test");
        assertEquals("Epic sadface: Username and password do not match any user in this service",
                loginPage.getErrorMessage());
    }
}



