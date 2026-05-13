package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    @Override
    public ProductsPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ITEMS));
        return this;
    }

    @Step("Открытие страницы каталога товаров")
    public ProductsPage open() {
        driver.get(MAIN_URL + "/inventory.html");
        return this;
    }

    @Step("Получение названия страницы")
    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }


    //ITEM NAME------------------------------------------------------
    @Step("Получение названия товара по его порядковому номеру '{itemNumber}'")
    public String getItemNameByNumber(int itemNumber) {
        List<WebElement> items = driver.findElements(ITEMS);
        if (itemNumber < 0 || itemNumber >= items.size()) {
            throw new IllegalArgumentException("Товар под номером " + itemNumber +
                    " не найден. Всего товаров на странице: " + items.size());
        }
        return items.get(itemNumber).findElement(ITEM_NAME).getText();
    }

    @Step("Получение названия всех товаров на странице")
    public List<String> allItemNames() {
        List<WebElement> items = driver.findElements(ITEMS);
        List<String> itemNames = new ArrayList<>();
        for (WebElement item : items) {
            itemNames.add(item.findElement(ITEM_NAME).getText());
        }
        return itemNames;
    }

    //ITEM PRICE------------------------------------------------------
    @Step("Получение цены товара по названию '{itemName}'")
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

    @Step("Получение цены товара по его порядковому номеру '{itemNumber}'")
    public String getItemPriceByNumber(int itemNumber) {
        List<WebElement> items = driver.findElements(ITEMS);
        if (itemNumber < 0 || itemNumber >= items.size()) {
            throw new IllegalArgumentException("Товар под номером " + itemNumber +
                    " не найден. Всего товаров на странице: " + items.size());
        }
        return items.get(itemNumber).findElement(ITEM_PRICE).getText();
    }

    //ITEM DESC------------------------------------------------------
    @Step("Получение описания товара по его порядковому номеру '{itemNumber}'")
    public String getItemDescByNumber(int itemNumber) {
        List<WebElement> items = driver.findElements(ITEMS);
        if (itemNumber < 0 || itemNumber >= items.size()) {
            throw new IllegalArgumentException("Товар под номером " + itemNumber +
                    " не найден. Всего товаров на странице: " + items.size());
        }
        return items.get(itemNumber).findElement(ITEM_DESC).getText();
    }

    @Step("Получение описания товара по его названию '{itemName}'")
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
    @Step("Добавление товара в корзину по его порядковому номеру '{itemNumber}'")
    public ProductsPage addToCartByNumber(int itemNumber) {
        List<WebElement> items = driver.findElements(ITEMS);
        if (itemNumber < 0 || itemNumber >= items.size()) {
            throw new IllegalArgumentException("Товар под номером " + itemNumber +
                    " не найден. Всего товаров на странице: " + items.size());
        }
        items.get(itemNumber).findElement(ADD_TO_CART_BUTTON).click();
        return new ProductsPage(driver);
    }

    @Step("Добавление товара в корзину по его названию '{itemName}'")
    public ProductsPage addToCartByName(String itemName) {
        List<WebElement> items = driver.findElements(ITEMS);
        for (WebElement container : items) {
            String currentName = container.findElement(ITEM_NAME).getText();
            if (currentName.equals(itemName)) {
                container.findElement(ADD_TO_CART_BUTTON).click();
                return new ProductsPage(driver);
            }
        }
        throw new RuntimeException("Товар '" + itemName + "' не найден в списке товаров!");
    }

    @Step("Добавление всех товаров на странице в корзину")
    public ProductsPage addToCartMax() {
        List<WebElement> items = driver.findElements(ITEMS);
        for (WebElement item : items) {
            item.findElement(ADD_TO_CART_BUTTON).click();
        }
        return new ProductsPage(driver);
    }

    //REMOVE FROM CART------------------------------------------------------
    @Step("Удаление товара из корзины по его порядковому номеру '{itemNumber}'")
    public void removeFromCartByNumber(int itemNumber) {
        List<WebElement> items = driver.findElements(ITEMS);
        if (itemNumber < 0 || itemNumber >= items.size()) {
            throw new IllegalArgumentException("Товар под номером " + itemNumber +
                    " не найден. Всего товаров на странице: " + items.size());
        }
        items.get(itemNumber).findElement(REMOVE_BUTTON).click();
    }

    @Step("Удаление товара из корзины по его названию '{itemName}'")
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

    @Step("Получение текста с кнопки 'Add to cart/Remove' по порядковому номеру товара '{itemNumber}'")
    public String getButtonTextByNumber(int itemNumber) {
        List<WebElement> items = driver.findElements(ITEMS);
        if (itemNumber < 0 || itemNumber >= items.size()) {
            throw new IllegalArgumentException("Товар под номером " + itemNumber +
                    " не найден. Всего товаров на странице: " + items.size());
        }
        return items.get(itemNumber).findElement(ANY_BUTTON).getText();
    }

    @Step("Получение текста с кнопки 'Add to cart/Remove' по его названию '{itemName}'")
    public String getButtonTextByName(String itemName) {
        List<WebElement> items = driver.findElements(ITEMS);
        for (WebElement container : items) {
            String currentName = container.findElement(ITEM_NAME).getText();
            if (currentName.equals(itemName)) {
                return container.findElement(ANY_BUTTON).getText();
            }
        }
        throw new NoSuchElementException("Товар с названием '" + itemName + "' не найден на странице!");
    }

    @Step("Клик по кнопке со значком корзины/переход на страницу корзины")
    public CartPage goToCart() {
        driver.findElement(GO_TO_CART).click();
        return new CartPage(driver);
    }
}
