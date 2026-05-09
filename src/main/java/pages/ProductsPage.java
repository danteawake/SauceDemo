package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends BasePage {

    private final By ITEMS = By.className("inventory_item");
    private final By ITEM_NAME = By.className("inventory_item_name");
    private final By ITEM_PRICE = By.className("inventory_item_price");
    private final By ITEM_DESC = By.className("inventory_item_desc");
    private final By ADD_TO_CART_BUTTON = By.cssSelector("button[id^='add-to-cart']");
    private final By SORTED_FILTER = By.className("product_sort_container");
    private final By REMOVE_BUTTON = By.cssSelector("[data-test^='remove-']");
    private final By ANY_BUTTON = By.className("btn_inventory");
    private final By ITEM_IMG = By.className("inventory_item_img");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(MAIN_URL + "/inventory.html");
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    //ITEM NAME------------------------------------------------------
    public String getItemNameByNumber(int itemNumber) {
        List<WebElement> items = driver.findElements(ITEMS);
        if (itemNumber < 0 || itemNumber >= items.size()) {
            throw new IllegalArgumentException("Товар под номером " + itemNumber +
                    " не найден. Всего товаров на странице: " + items.size());
        }
        return items.get(itemNumber).findElement(ITEM_NAME).getText();
    }

    public List<String> allItemNames() {
        List<WebElement> items = driver.findElements(ITEMS);
        List<String> itemNames = new ArrayList<>();
        for (WebElement item : items) {
            itemNames.add(item.findElement(ITEM_NAME).getText());
        }
        return itemNames;
    }

    //ITEM PRICE------------------------------------------------------
    public String getItemPriceByName(String itemName) {
        List<WebElement> items = driver.findElements(ITEMS);
        for (WebElement container : items) {
            String currentName = container.findElement(ITEM_NAME).getText();
            if (currentName.equals(itemName)) {
                return container.findElement(ITEM_PRICE).getText();
            }
        }
        throw new RuntimeException("Товар '" + itemName + "' не найден в списке товаров!");
    }

    public String getItemPriceByNumber(int itemNumber) {
        List<WebElement> items = driver.findElements(ITEMS);
        if (itemNumber < 0 || itemNumber >= items.size()) {
            throw new IllegalArgumentException("Товар под номером " + itemNumber +
                    " не найден. Всего товаров на странице: " + items.size());
        }
        return items.get(itemNumber).findElement(ITEM_PRICE).getText();
    }

    //ITEM DESC------------------------------------------------------
    public String getItemDescByNumber(int itemNumber) {
        List<WebElement> items = driver.findElements(ITEMS);
        if (itemNumber < 0 || itemNumber >= items.size()) {
            throw new IllegalArgumentException("Товар под номером " + itemNumber +
                    " не найден. Всего товаров на странице: " + items.size());
        }
        return items.get(itemNumber).findElement(ITEM_DESC).getText();
    }

    public String getItemDescByName(String itemName) {
        List<WebElement> items = driver.findElements(ITEMS);
        for (WebElement container : items) {
            String currentName = container.findElement(ITEM_NAME).getText();
            if (currentName.equals(itemName)) {
                return container.findElement(ITEM_DESC).getText();
            }
        }
        throw new RuntimeException("Товар '" + itemName + "' не найден в списке товаров!");
    }

    //ADD TO CART------------------------------------------------------
    public void addToCartByNumber(int itemNumber) {
        List<WebElement> items = driver.findElements(ITEMS);
        if (itemNumber < 0 || itemNumber >= items.size()) {
            throw new IllegalArgumentException("Товар под номером " + itemNumber +
                    " не найден. Всего товаров на странице: " + items.size());
        }
        items.get(itemNumber).findElement(ADD_TO_CART_BUTTON).click();
    }

    public void addToCartByName(String itemName) {
        List<WebElement> items = driver.findElements(ITEMS);
        for (WebElement container : items) {
            String currentName = container.findElement(ITEM_NAME).getText();
            if (currentName.equals(itemName)) {
                container.findElement(ADD_TO_CART_BUTTON).click();
                return;
            }
        }
        throw new RuntimeException("Товар '" + itemName + "' не найден в списке товаров!");
    }

    public void addToCartMax() {
        List<WebElement> items = driver.findElements(ITEMS);
        for (WebElement item : items) {
            item.findElement(ADD_TO_CART_BUTTON).click();
        }
    }

    //REMOVE FROM CART------------------------------------------------------
    public void removeFromCartByNumber(int itemNumber) {
        List<WebElement> items = driver.findElements(ITEMS);
        if (itemNumber < 0 || itemNumber >= items.size()) {
            throw new IllegalArgumentException("Товар под номером " + itemNumber +
                    " не найден. Всего товаров на странице: " + items.size());
        }
        items.get(itemNumber).findElement(REMOVE_BUTTON).click();
    }

    public void removeFromCartByName(String itemName) {
        List<WebElement> items = driver.findElements(ITEMS);
        for (WebElement container : items) {
            String currentName = container.findElement(ITEM_NAME).getText();
            if (currentName.equals(itemName)) {
                container.findElement(REMOVE_BUTTON).click();
                return;
            }
        }
        throw new RuntimeException("Товар '" + itemName + "' не найден в списке товаров!");
    }

    public String getButtonText(int itemNumber) {
        List<WebElement> items = driver.findElements(ITEMS);
        if (itemNumber < 0 || itemNumber >= items.size()) {
            throw new IllegalArgumentException("Товар под номером " + itemNumber +
                    " не найден. Всего товаров на странице: " + items.size());
        }
        return items.get(itemNumber).findElement(ANY_BUTTON).getText();
    }

    public void goToCart() {
        driver.findElement(GO_TO_CART).click();
    }
}
