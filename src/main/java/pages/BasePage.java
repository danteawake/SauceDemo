package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {

    public final String MAIN_URL = "https://www.saucedemo.com/";
    protected final By TITLE = By.cssSelector("[data-test=title]");
    protected final By APP_LOGO = By.className("app_logo");
    protected final By GO_TO_CART = By.className("shopping_cart_link");
    protected final By BURGER_MENU_BUTTON = By.id("react-burger-menu-btn");
    protected final By FOOTER = By.cssSelector("[data-test=footer]");
    protected final By SOCIAL_TWITTER = By.cssSelector("[data-test=social-twitter]");
    protected final By SOCIAL_FACEBOOK = By.cssSelector("[data-test=social-facebook]");
    protected final By SOCIAL_LINKEDIN = By.cssSelector("[data-test=social-linkedin]");
    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
}
