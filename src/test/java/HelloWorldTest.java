import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HelloWorldTest {

    @Before
    public void startUp() {

        WebDriverManager.chromedriver().setup();
    }

    @Test
    public void testHelloWorld() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://qa-mesto.praktikum-services.ru/");
        driver.findElement(By.id("email")).sendKeys("papa@papa.com");
        driver.findElement(By.id("password")).sendKeys("123");
        driver.findElement(By.xpath(".//button[@class='auth-form__button']")).click();
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("header__user")));
        driver.findElement(By.className("profile__image")).click();
        driver.findElement(By.id("owner-avatar")).sendKeys("https://code.s3.yandex.net/qa-automation-engineer/java/files/paid-track/sprint1/avatarSelenium.png");
        new WebDriverWait(driver, 10);
        WebElement form = driver.findElement(By.xpath("//form[@name='edit-avatar']"));

// Внутри найденной формы находим кнопку с текстом 'Сохранить'
        WebElement saveButton = form.findElement(By.xpath(".//button[text()='Сохранить']"));

// Кликаем на найденную кнопку
        saveButton.click();
        driver.findElement(By.xpath(".//button[@class='profile__add-button']")).click();
        WebElement a = driver.findElement(By.xpath("//form[@name='new-card']"));
        driver.findElement(By.id("place-name")).sendKeys("Пага");
        driver.findElement(By.id("place-link")).sendKeys("https://code.s3.yandex.net/qa-automation-engineer/java/files/paid-track/sprint1/photoSelenium.jpg");
        new WebDriverWait(driver, 3);
        a.findElement(By.xpath(".//button[text()='Сохранить']")).click();
        new WebDriverWait(driver, 3);
        WebElement as = driver.findElement(By.cssSelector(".places__item"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", as);
    }
}
