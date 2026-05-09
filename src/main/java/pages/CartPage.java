package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    private final By CART_ITEM = By.className("cart_item");
    private final By CART_ITEM_NAME = By.className("inventory_item_name");
    private final By CART_ITEM_PRICE = By.className("inventory_item_price");
    private final By CART_ITEM_DESC = By.className("inventory_item_desc");
    private final By REMOVE_BUTTON = By.cssSelector("[data-test^='remove-']");
    private final By CONTINUE_SHOPPING_BUTTON = By.id("continue-shopping");
    private final By CHECKOUT_BUTTON = By.cssSelector("[data-test=checkout]");
    private final By QUANTITY_LABEL = By.cssSelector("[data-test=cart-quantity-label]");
    private final By CART_DESC_LABEL = By.cssSelector("[data-test=cart-desc-label]");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(MAIN_URL + "/cart.html");
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    //ITEM NAME------------------------------------------------------
    public String getItemNameByNumber(int itemNumber) {
        List<WebElement> items = driver.findElements(CART_ITEM);
        if (itemNumber < 0 || itemNumber >= items.size()) {
            throw new IllegalArgumentException("Товар под номером " + itemNumber +
                    " не найден. Всего товаров на странице: " + items.size());
        }
        return items.get(itemNumber).findElement(CART_ITEM_NAME).getText();
    }

    public List<String> getAllItemsNames() {
        List<WebElement> items = driver.findElements(CART_ITEM);
        List<String> itemNames = new ArrayList<>();
        for (WebElement item : items) {
            itemNames.add(item.findElement(CART_ITEM_NAME).getText());
        }
        return itemNames;
    }

    //ITEM PRICE------------------------------------------------------
    public String getItemPriceByName(String itemName) {
        List<WebElement> items = driver.findElements(CART_ITEM);
        for (WebElement container : items) {
            String currentName = container.findElement(CART_ITEM_NAME).getText();
            if (currentName.equals(itemName)) {
                return container.findElement(CART_ITEM_PRICE).getText();
            }
        }
        throw new RuntimeException("Товар '" + itemName + "' не найден в списке товаров!");
    }

    public String getItemPriceByNumber(int itemNumber) {
        List<WebElement> items = driver.findElements(CART_ITEM);
        if (itemNumber < 0 || itemNumber >= items.size()) {
            throw new IllegalArgumentException("Товар под номером " + itemNumber +
                    " не найден. Всего товаров на странице: " + items.size());
        }
        return items.get(itemNumber).findElement(CART_ITEM_PRICE).getText();
    }

    //ITEM DESC------------------------------------------------------
    public String getItemDescByNumber(int itemNumber) {
        List<WebElement> items = driver.findElements(CART_ITEM);
        if (itemNumber < 0 || itemNumber >= items.size()) {
            throw new IllegalArgumentException("Товар под номером " + itemNumber +
                    " не найден. Всего товаров на странице: " + items.size());
        }
        return items.get(itemNumber).findElement(CART_ITEM_DESC).getText();
    }

    public String getItemDescByName(String itemName) {
        List<WebElement> items = driver.findElements(CART_ITEM);
        for (WebElement container : items) {
            String currentName = container.findElement(CART_ITEM_NAME).getText();
            if (currentName.equals(itemName)) {
                return container.findElement(CART_ITEM_DESC).getText();
            }
        }
        throw new RuntimeException("Товар '" + itemName + "' не найден в списке товаров!");
    }

    public void removeFromCart(int itemNumber) {
        List<WebElement> items = driver.findElements(CART_ITEM);
        if (itemNumber < 0 || itemNumber >= items.size()) {
            throw new IllegalArgumentException("Товар под номером " + itemNumber +
                    " не найден. Всего товаров на странице: " + items.size());
        }
        items.get(itemNumber).findElement(REMOVE_BUTTON).click();
    }
}
