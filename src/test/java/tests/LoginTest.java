package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class LoginTest extends BaseTest {

    @Test(groups = {"smoke", "regression"},
            testName = "Успешный вход в систему",
            description = "Успешный вход в систему")
    @Description("Успешный вход в систему")
    @Epic("SauceDemo")
    @Feature("Login")
    @Story("Authorisation")
    @Severity(SeverityLevel.CRITICAL)
    @Link("Confluence")
    @TmsLink("Jira")
    @Issue("Jira")
    @Owner("Egorov.OI")
    public void checkLoginWithPositiveValue() {
        loginPage.open()
                .isPageOpened()
                .login("standard_user", "secret_sauce");
        assertEquals("Products", productsPage.getTitle());
    }

    @Test(groups = {"regression"},
            testName = "Проверка ошибки при пустом пароле",
            description = "Проверка ошибки при пустом пароле")
    @Description("Проверка ошибки при пустом пароле")
    @Epic("SauceDemo")
    @Feature("Login")
    @Story("Authorisation")
    @Severity(SeverityLevel.NORMAL)
    @Link("Confluence")
    @TmsLink("Jira")
    @Issue("Jira")
    @Owner("Egorov.OI")
    public void checkLoginWithEmptyPassword() {
        loginPage.open()
                .isPageOpened()
                .login("standard_user", "");
        assertEquals("Epic sadface: Password is required", loginPage.getErrorMessage());
    }

    @Test(groups = {"regression"},
            testName = "Проверка ошибки при пустом логине",
            description = "Проверка ошибки при пустом логине")
    @Description("Проверка ошибки при пустом логине")
    @Epic("SauceDemo")
    @Feature("Login")
    @Story("Authorisation")
    @Severity(SeverityLevel.NORMAL)
    @Link("Confluence")
    @TmsLink("Jira")
    @Issue("Jira")
    @Owner("Egorov.OI")
    public void checkLoginWithEmptyUser() {
        loginPage.open()
                .isPageOpened()
                .login("", "secret_sauce");
        assertEquals("Epic sadface: Username is required", loginPage.getErrorMessage());
    }

    @Test(groups = {"regression"},
            testName = "Проверка ошибки при неверных данных",
            description = "Проверка ошибки при неверных данных")
    @Description("Проверка ошибки при неверных данных")
    @Epic("SauceDemo")
    @Feature("Login")
    @Story("Authorisation")
    @Severity(SeverityLevel.NORMAL)
    @Link("Confluence")
    @TmsLink("Jira")
    @Issue("Jira")
    @Owner("Egorov.OI")
    public void checkLoginWithNegativeValue() {
        loginPage.open()
                .isPageOpened()
                .login("test", "test");
        assertEquals("Epic sadface: Username and password do not match any user in this service",
                loginPage.getErrorMessage());
    }

    //DataProvider
    @Test(
            groups = {"regression"},
            dataProvider = "negativeRegressLoginData",
            description = "Негативные проверки логина"
    )
    @Description("Негативные проверки логина")
    @Epic("SauceDemo")
    @Feature("Login")
    @Story("Authorisation")
    @Severity(SeverityLevel.NORMAL)
    @Link("Confluence")
    @TmsLink("Jira")
    @Issue("Jira")
    @Owner("Egorov.OI")
    public void checkNegativeLoginDP(String user, String password, String expectedError) {
        loginPage.open()
                .isPageOpened()
                .login(user, password);
        assertEquals(expectedError, loginPage.getErrorMessage());
    }

    @DataProvider(name = "negativeRegressLoginData")
    public Object[][] negativeData() {
        return new Object[][]{
                {"standard_user", "", "Epic sadface: Password is required"},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"test", "test", "Epic sadface: Username and password do not match any user in this service"}
        };
    }
}



