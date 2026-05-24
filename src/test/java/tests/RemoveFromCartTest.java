package tests;

import io.qameta.allure.*;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;

public class RemoveFromCartTest extends BaseTest {

    private final List<String> allItemNames = List.of(
            "Sauce Labs Backpack",
            "Sauce Labs Bike Light",
            "Sauce Labs Bolt T-Shirt",
            "Sauce Labs Fleece Jacket",
            "Sauce Labs Onesie",
            "Test.allTheThings() T-Shirt (Red)"
    );

    @Test(groups = {"regression"},
            testName = "Удаление одного из нескольких товаров со страницы каталога",
            description = "Удаление одного из нескольких товаров со страницы каталога")
    @Description("Удаление одного из нескольких товаров со страницы каталога")
    @Epic("SauceDemo")
    @Feature("Login")
    @Story("Cart")
    @Severity(SeverityLevel.CRITICAL)
    @Link("Confluence")
    @TmsLink("Jira")
    @Issue("Jira")
    @Owner("Egorov.OI")
    public void removeItemOfManyFromProductPage(@Optional("3") int itemIndex) {
        String removedItemName = "Sauce Labs Onesie";
        loginPage.open()
                .login(user, password)
                .isPageOpened()
                .addToCartMax()
                .goToCart()
                .isPageOpened();
        assertEquals(allItemNames, cartPage.getAllItemsNames());
        productsPage.open()
                .isPageOpened()
                .removeFromCartByName(removedItemName);
        assertEquals("Add to cart", productsPage.getButtonTextByName(removedItemName));
        cartPage.open()
                .isPageOpened();
        assertEquals(5, cartPage.getAllItemsNames().size());
        assertFalse("Товар " + removedItemName + " все еще отображается в корзине!",
                cartPage.getAllItemsNames().contains(removedItemName));
    }

    @Test(groups = {"regression"},
            testName = "Удаление одного из нескольких товаров прямо в корзине",
            description = "Удаление одного из нескольких товаров прямо в корзине")
    @Description("Удаление одного из нескольких товаров прямо в корзине")
    @Epic("SauceDemo")
    @Feature("Login")
    @Story("Cart")
    @Severity(SeverityLevel.CRITICAL)
    @Link("Confluence")
    @TmsLink("Jira")
    @Issue("Jira")
    @Owner("Egorov.OI")
    public void removeItemOfManyFromCart(@Optional("3") int itemIndex) {
        String removedItemName = "Sauce Labs Fleece Jacket";
        loginPage.open()
                .login(user, password)
                .isPageOpened().addToCartMax()
                .goToCart().isPageOpened();
        assertEquals(allItemNames, cartPage.getAllItemsNames());
        cartPage.removeFromCart(itemIndex);
        assertEquals(5, cartPage.getAllItemsNames().size());
        assertFalse("Товар " + removedItemName + " все еще отображается в корзине!",
                cartPage.getAllItemsNames().contains(removedItemName));
    }

    @Test(groups = {"smoke", "regression"},
            testName = "Добавление и удаление товара из корзины",
            description = "Добавление и удаление товара из корзины")
    @Description("Добавление и удаление товара из корзины")
    @Epic("SauceDemo")
    @Feature("Login")
    @Story("Cart")
    @Severity(SeverityLevel.CRITICAL)
    @Link("Confluence")
    @TmsLink("Jira")
    @Issue("Jira")
    @Owner("Egorov.OI")
    public void removeItemLast(@Optional("3") int itemIndex) {
        String removedItemName = "Sauce Labs Fleece Jacket";
        loginPage.open()
                .login(user, password)
                .isPageOpened()
                .addToCartByNumber(itemIndex)
                .goToCart()
                .isPageOpened();
        assertEquals(removedItemName, cartPage.getItemNameByNumber(0));
        cartPage.removeFromCart(0);
        assertEquals(0, cartPage.getAllItemsNames().size());
    }
}