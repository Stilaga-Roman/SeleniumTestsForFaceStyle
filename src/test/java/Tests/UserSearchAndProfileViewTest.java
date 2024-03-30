package Tests;

import Helpers.AuthHelper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserSearchAndProfileViewTest {

    private AuthHelper authHelper;

    @Before
    public void setUp() {
        authHelper = new AuthHelper();
        authHelper.authCustomer(authHelper.getLogin(), authHelper.getPassword());
    }

    @Test
    public void testUserNavigationAndNameDisplay() {
        navigateToSearchAndFillLogin(authHelper.getLogin());
        authHelper.getDriver().findElement(By.name("submit")).click();
        authHelper.getDriver().findElement(By.xpath("//a[contains(@href, 'login=" + authHelper.getLogin() + "')]")).click();
    }

    @Test
    public void testSearchWithInvalidLogin() {
        String invalidLogin = "incorrectUser123";
        navigateToSearchAndFillLogin(invalidLogin);
        authHelper.getDriver().findElement(By.cssSelector("input[type='submit']")).click();
        WebDriverWait wait = new WebDriverWait(authHelper.getDriver(), 10);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//*[contains(text(), 'Неверный логин')]")));
        Assert.assertTrue("Сообщение об ошибке 'Неверный логин' не отображается", errorMessage.isDisplayed());
    }

    private void navigateToSearchAndFillLogin(String login) {
        authHelper.getDriver().findElement(By.linkText("Поиск")).click();
        WebElement loginInput = authHelper.getDriver().findElement(By.name("login"));
        loginInput.sendKeys(login);
    }

    @After
    public void tearDown() {
        authHelper.closeBrowser();
    }
}
