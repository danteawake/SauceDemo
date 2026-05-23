package tests;

import io.qameta.allure.*;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

@Log4j2
public class AddToCartTest extends BaseTest {

    @Test(groups = {"smoke", "regression"},
            testName = "Добавление одного товара в корзину",
            description = "Добавление одного товара в корзину",
            priority = 2)
    @Description("Добавление одного товара в корзину")
    @Epic("SauceDemo")
    @Feature("Login")
    @Story("Cart")
    @Severity(SeverityLevel.CRITICAL)
    @Link("Confluence")
    @TmsLink("Jira")
    @Issue("Jira")
    @Owner("Egorov.OI")
    public void addToCartOne(@Optional("3") int itemIndex) {
        loginPage.open()
                .isPageOpened()
                .login(user, password)
                .addToCartByNumber(itemIndex)
                .goToCart()
                .isPageOpened();
        assertEquals("Sauce Labs Fleece Jacket", cartPage.getItemName());
    }

    @Test(groups = {"regression"},
            testName = "Добавление всех доступных товаров в корзину",
            description = "Добавление всех доступных товаров в корзину",
            priority = 1)
    @Description("Добавление всех доступных товаров в корзину")
    @Epic("SauceDemo")
    @Feature("Login")
    @Story("Cart")
    @Severity(SeverityLevel.NORMAL)
    @Link("Confluence")
    @TmsLink("Jira")
    @Issue("Jira")
    @Owner("Egorov.OI")
    public void addToCartMax() {
        List<String> expectedItemNames = List.of(
                "Sauce Labs Backpack",
                "Sauce Labs Bike Light",
                "Sauce Labs Bolt T-Shirt",
                "Sauce Labs Fleece Jacket",
                "Sauce Labs Onesie",
                "Test.allTheThings() T-Shirt (Red)"
        );
        loginPage.open()
                .isPageOpened()
                .login(user, password)
                .isPageOpened()
                .addToCartMax()
                .goToCart()
                .isPageOpened();
        assertEquals(expectedItemNames, cartPage.getAllItemsNames());
    }
}