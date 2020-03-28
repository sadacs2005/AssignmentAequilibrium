package testCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import po.CartPage;
import po.LoginPage;
import utilities.Base;

public class LoginPageTest extends Base {
    WebDriver driver;
    LoginPage lp;
    CartPage cp;

    @BeforeTest()
    public void test00PrerequisiteInitialization() throws IOException {
        driver = initializeDriver();
        lp = new LoginPage(driver);
        cp=new CartPage(driver);
    }

    @BeforeMethod
    public void test00LaunchTheBrowser() {
        driver.get(prop.getProperty("Sauce_Demo_URL"));
    }

    //This test is to verify that password field is mandatory
    @Test
    public void test01UserNameWithoutPwd() {
        String userMessage = "Password is required";
        lp.enterUsername(prop.getProperty("UserName"));
        lp.clickOnLogin();
        lp.verifyMessage(userMessage);
    }

    //This test is to verify that user is able to login with valid credentials
    @Test(dataProvider = "getValidCredentials")
    public void test02EnterValidCredentials(String username,String password) {
        lp.enterUsername(username);
        lp.enterPassword(password);
        lp.clickOnLogin();
        cp.verifyLoginIsSuccessful();
    }

    //This test is to verify username is mandatory
    @Test
    public void test03PwdWithoutUsername() {
        String userMessage = "Username is required";
        lp.enterPassword(prop.getProperty("Password"));
        lp.clickOnLogin();
        lp.verifyMessage(userMessage);
    }

    //This test is to verify login not possible with invalid credentials
   @Test
    public void test04EnterInvalidCredentials() {
        String userMessage = "Username and password do not match any user in this service";
        lp.enterUsername(prop.getProperty("InvalidUserName"));
        lp.enterPassword(prop.getProperty("InvalidPassword"));
        lp.clickOnLogin();
        lp.verifyMessage(userMessage);
    }
    
   //This test is to verify login not possible with locked user
    @Test
    public void test05LoginWithLockedUser() {
        String userMessage = "Sorry, this user has been locked out.";
        lp.enterUsername(prop.getProperty("LockerUserName"));
        lp.enterPassword(prop.getProperty("Password"));
        lp.clickOnLogin();
        lp.verifyMessage(userMessage);
    }
    
    //This test is to verify both username and password are mandatory
    @Test
    public void test06NoUsernamePwdEntered() {
        String userMessage = "Username is required";
        lp.clickOnLogin();
        lp.verifyMessage(userMessage);
    }
    
    //This test is to verify  user is redirected to login page on logout and clicks on browser back button
    @Test
    public void test10ClickBackBtnOnLogout() {
        lp.enterUsername(prop.getProperty("UserName"));
        lp.enterPassword(prop.getProperty("Password"));
        lp.clickOnLogin();
        cp.logout();
        driver.navigate().back();
        lp.verifyloginPage();
    }
    
    

    @DataProvider
    public Object[][] getValidCredentials() {
        Object[][] ob = new Object[3][2];
        ob[0][0] = "standard_user";
        ob[1][0] = "problem_user";
        ob[2][0] = "performance_glitch_user";
        ob[0][1] = ob[1][1] = ob[2][1] = "secret_sauce";
        return ob;

    }

}
