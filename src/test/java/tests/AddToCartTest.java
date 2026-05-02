package tests;

import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class AddToCartTest extends BaseTest {

    @Test(groups = {"smoke", "regression"}, description = "Добавление одного товара в корзину")
    public void addToCartOne(@Optional("3") int itemIndex) {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals("Products", productsPage.getTitle());
        //productsPage------------------------------------------------------
        String itemName = productsPage.getItemNameByNumber(itemIndex);
        String itemPrice = productsPage.getItemPriceByNumber(itemIndex);
        productsPage.addToCartByNumber(itemIndex);
        productsPage.goToCart();
        assertEquals("Your Cart", cartPage.getTitle());
        //cartPage------------------------------------------------------
        assertEquals(cartPage.getItemPriceByName(itemName), itemPrice);
    }

    @Test(groups = {"regression"}, description = "Добавление всех доступных товаров в корзину")
    public void addToCartMax() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals("Products", productsPage.getTitle());
        //productsPage------------------------------------------------------
        productsPage.addToCartMax();
        List<String> allItemNames = productsPage.allItemNames();
        productsPage.goToCart();
        assertEquals("Your Cart", cartPage.getTitle());
        //cartPage------------------------------------------------------
        assertEquals(allItemNames, cartPage.getAllItemsNames());
    }
}