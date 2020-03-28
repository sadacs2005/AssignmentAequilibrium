package po;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.CommonMethods;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='user-name']")
    WebElement username;

    @FindBy(xpath = "//input[@id='password']")
    WebElement password;

    @FindBy(xpath = "//input[@value='LOGIN']")
    WebElement loginbtn;

    @FindBy(xpath = "//h3[text()='<msg>']")
    WebElement msg;

    public WebElement getMessageElem(String msg) {
        return driver.findElement(By.xpath("//h3[text()='" + msg + "']"));
    }

    public void enterUsername(String uname) {
        CommonMethods.clearAndInput(username, uname);
    }

    public void enterPassword(String pwd) {
        CommonMethods.clearAndInput(password, pwd);
    }

    public void clickOnLogin() {
        loginbtn.click();

    }

    public void verifyMessage(String userMessage) {
        assertTrue(CommonMethods.isElementPresent(getMessageElem(userMessage)), "The user message " + userMessage + " is not displayed");
        ;
    }

    public void verifyloginPage() {
       assertTrue(CommonMethods.isElementPresent(loginbtn), "User is not in the login page");
        
    }

}
