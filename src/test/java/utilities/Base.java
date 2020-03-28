package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

public class Base {
    public static WebDriver driver = null;
    public Properties       prop   = new Properties();

    @Test
    public WebDriver initializeDriver() throws IOException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\config\\global.properties");
        prop.load(fis);
        if (prop.getProperty("browser").contains("Chrome")) {
            ChromeOptions options = new ChromeOptions();
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\chromedriver.exe");
            if (prop.getProperty("browser").contains("headless")) {
                options.addArguments("headless");
            }
            driver = new ChromeDriver(options);

        } else if (prop.getProperty("browser").equals("Firefox")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\geckodriver.exe");
            driver = new FirefoxDriver();
        } else {

        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return driver;
    }
    
    
    @AfterSuite
    public void tearDown() {
        driver.quit();
    }


    public void takeScreenshots(String name) throws IOException {
        File f = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String destinationPath = System.getProperty("user.dir")+"\\Screenshots\\"+name+"Screenshot.png";
        FileUtils.copyFile(f, new File(destinationPath));        
    }

}
