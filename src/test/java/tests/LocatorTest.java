package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LocatorTest extends BaseTest {

    @Test
    public void checkLocator() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        //Практика
        // id
        By addToCartBackpack = By.id("add-to-cart-sauce-labs-backpack");

        // name
        By sortContainer = By.name("product_sort_container");

        // classname
        By itemName = By.className("inventory_item_name");

        // tagname
        By button = By.tagName("button");

        // linktext
        By twitterLink = By.linkText("Twitter");

        // partiallinktext
        By partialTwitter = By.partialLinkText("Twit");

        // XPath
        By xpathByAttribute = By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']");
        By xpathByText = By.xpath("//div[text()='Sauce Labs Backpack']");
        By xpathContainsAttribute = By.xpath("//button[contains(@id,'add-to-cart')]");
        By xpathContainsText = By.xpath("//div[contains(text(),'Sauce Labs')]");
        By xpathAncestor = By.xpath("//div[text()='Sauce Labs Backpack']/ancestor::div[@class='inventory_item']");
        By xpathDescendant = By.xpath("//div[@class='inventory_list']/descendant::button");
        By xpathFollowing = By.xpath("//div[text()='Sauce Labs Backpack']/following::button");
        By xpathParent = By.xpath("//div[text()='Sauce Labs Backpack']/parent::div");
        By xpathPreceding = By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']/preceding::div[@class='inventory_item_name']");
        By xpathAnd = By.xpath("//button[@id='add-to-cart-sauce-labs-backpack' and contains(@class,'btn')]");

        // CSS
        By cssClass = By.cssSelector(".inventory_item_name");
        By cssClassDown = By.cssSelector(".btn.btn_primary");
        By cssClassUp = By.cssSelector(".inventory_list .inventory_item_name");
        By cssId = By.cssSelector("#add-to-cart-sauce-labs-backpack");
        By cssTagname = By.cssSelector("button");
        By cssTagnameClass = By.cssSelector("button.btn_primary");
        By cssAttributeEquals = By.cssSelector("button[id='add-to-cart-sauce-labs-backpack']");
        By cssAttributeTilde = By.cssSelector("button[class~='btn']");
        By cssAttributePipe = By.cssSelector("div[id|='inventory']");
        By cssAttributeCaret = By.cssSelector("button[id^='add-to-cart']");
        By cssAttributeDollar = By.cssSelector("button[id$='backpack']");
        By cssAttributeAsterisk = By.cssSelector("button[id*='add-to-cart']");
    }
}
