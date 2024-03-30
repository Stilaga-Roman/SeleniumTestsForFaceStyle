package Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AuthHelper {

    private final Basa basa;
    
    public AuthHelper() {
        this.basa = new Basa();
    }

    public WebDriver getDriver() {
        return basa.driver;
    }

    public String getLogin() {
        return "Selenium";
    }

    public String getPassword() {
        return "123456";
    }
    
    public void authCustomer(String login, String password) {
        basa.open();
        
        basa.driver.findElement(By.name("login")).sendKeys(login);
        basa.driver.findElement(By.name("password")).sendKeys(password);
        basa.driver.findElement(By.xpath(".//button[@class='register_vhod']")).click();
    }
    
    public void closeBrowser() {
        basa.closeBrowser();
    }
}

