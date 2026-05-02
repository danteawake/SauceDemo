package tests;

import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;

public class RemoveFromCartTest extends BaseTest {

    @Test(groups = {"regression"}, description = "Удаление одного из нескольких товаров со страницы каталога")
    public void removeItemOfManyFromProductPage(@Optional("3") int itemIndex) {
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
        productsPage.open();
        assertEquals("Products", productsPage.getTitle());
        //productsPage------------------------------------------------------
        productsPage.removeFromCartByNumber(itemIndex);
        String removedItemName = productsPage.getItemNameByNumber(itemIndex);
        assertEquals("Add to cart", productsPage.getButtonText(itemIndex));
        cartPage.open();
        assertEquals("Your Cart", cartPage.getTitle());
        //cartPage------------------------------------------------------
        assertEquals(allItemNames.size() - 1, cartPage.getAllItemsNames().size());
        assertFalse("Товар " + removedItemName + " все еще отображается в корзине!",
                cartPage.getAllItemsNames().contains(removedItemName));
    }

    @Test(groups = {"regression"}, description = "Удаление одного из нескольких товаров прямо в корзине")
    public void removeItemOfManyFromCart(@Optional("3") int itemIndex) {
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
        String removedItemName = cartPage.getItemNameByNumber(itemIndex);
        cartPage.removeFromCart(itemIndex);
        assertEquals(allItemNames.size() - 1, cartPage.getAllItemsNames().size());
        assertFalse("Товар " + removedItemName + " все еще отображается в корзине!",
                cartPage.getAllItemsNames().contains(removedItemName));
    }

    @Test(groups = {"smoke", "regression"}, description = "Добавление и удаление товара из корзины")
    public void removeItemLast(@Optional("3") int itemIndex) {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals("Products", productsPage.getTitle());
        //productsPage------------------------------------------------------
        productsPage.addToCartByNumber(itemIndex);
        String itemName = productsPage.getItemNameByNumber(itemIndex);
        String itemPrice = productsPage.getItemPriceByNumber(itemIndex);
        productsPage.goToCart();
        assertEquals("Your Cart", cartPage.getTitle());
        //cartPage------------------------------------------------------
        assertEquals(itemPrice, cartPage.getItemPriceByName(itemName));
        cartPage.removeFromCart(0);
        assertEquals(0, cartPage.getAllItemsNames().size());
    }
}