package utilities;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class CommonMethods {

    public static void clearAndInput(WebElement username, String uname) {
        username.clear();
        username.sendKeys(uname);
    }

    public static boolean isElementPresent(WebElement elem) {
        boolean isPresent = false;
        try {
            if (elem.isDisplayed()) {
                isPresent = true;
            }
        } catch (NoSuchElementException e) {
            isPresent = false;
        }
        return isPresent;
    }

}
