package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    public final String MAIN_URL = "https://www.saucedemo.com/";
    protected final By TITLE = By.cssSelector("[data-test=title]");
    protected final By APP_LOGO = By.className("app_logo");
    protected final By GO_TO_CART = By.className("shopping_cart_link");
    protected final By BURGER_MENU_BUTTON = By.id("react-burger-menu-btn");
    protected final By FOOTER = By.cssSelector("[data-test=footer]");
    protected final By SOCIAL_TWITTER = By.cssSelector("[data-test=social-twitter]");
    protected final By SOCIAL_FACEBOOK = By.cssSelector("[data-test=social-facebook]");
    protected final By SOCIAL_LINKEDIN = By.cssSelector("[data-test=social-linkedin]");

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public abstract BasePage isPageOpened();

    public abstract BasePage open();
}
