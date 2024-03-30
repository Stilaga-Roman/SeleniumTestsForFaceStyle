package Helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;


public class Basa {
    protected WebDriver driver;

    protected Basa() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    protected void open() {
        driver.get("http://u164539.test-handyhost.ru");
        ((JavascriptExecutor) driver).executeScript("document.querySelector('button').click();");
    }

    protected void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
