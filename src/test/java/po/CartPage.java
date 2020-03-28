package po;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.CommonMethods;

public class CartPage {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    @FindBy(xpath = "//div[@id='shopping_cart_container']")
    WebElement cartIcon;
    
    @FindBy(xpath = "//button[text()='Open Menu']")
    WebElement menuBtn;
    
    @FindBy(xpath = "//a[text()='Logout']")
    WebElement logout;

    public void verifyLoginIsSuccessful() {
        assertTrue(CommonMethods.isElementPresent(cartIcon), "Login to the application is unsuccessful");
    }

    public void logout() {
       menuBtn.click();
       logout.click();
    }

}
