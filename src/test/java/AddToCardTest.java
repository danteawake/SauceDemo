import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

public class AddToCardTest {
    private final By usernameFieldLocator = By.id("user-name");
    private final By passwordFieldLocator = By.id("password");
    private final By loginButtonLocator = By.id("login-button");

    private final String login = "standard_user";
    private final String password = "secret_sauce";

    @Test
    public void addToCart() {
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--incognito");
        options.addArguments("--disable_notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com/");
        SoftAssert softAssert = new SoftAssert();

        driver.findElement(usernameFieldLocator).sendKeys(login);
        driver.findElement(passwordFieldLocator).sendKeys(password);
        driver.findElement(loginButtonLocator).click();

        By itemsLocator = By.cssSelector(".inventory_item");
        By itemNameLocator = By.className("inventory_item_name");
        By itemPriceLocator = By.className("inventory_item_price");
        By itemAddToCartButtonLocator = By.cssSelector("button[id^='add-to-cart']");
        By cartLocator = By.cssSelector(".shopping_cart_link");

        WebElement itemContainer = driver.findElements(itemsLocator).get(3);
        String itemName = itemContainer.findElement(itemNameLocator).getText();
        String itemPrice = itemContainer.findElement(itemPriceLocator).getText();
        itemContainer.findElement(itemAddToCartButtonLocator).click();
        driver.findElement(cartLocator).click();

        By cartItemLocator = By.cssSelector(".cart_item");
        By cartItemNameLocator = By.cssSelector(".inventory_item_name");
        By cartItemPriceLocator = By.cssSelector(".inventory_item_price");

        WebElement cartItemContainer = driver.findElement(cartItemLocator);
        String cartItemName = cartItemContainer.findElement(cartItemNameLocator).getText();
        String cartItemPrice = cartItemContainer.findElement(cartItemPriceLocator).getText();

        softAssert.assertEquals(cartItemName, itemName);
        Assert.assertEquals(cartItemPrice, itemPrice);

        driver.quit();
        softAssert.assertAll();
    }
}