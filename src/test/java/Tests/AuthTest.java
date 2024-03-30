package Tests;

import Helpers.AuthHelper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AuthTest {

    private AuthHelper authHelper;

    @Before
    public void setUp() {
        authHelper = new AuthHelper();
    }

    @Test
    public void testAuthCustomer() {
        authHelper.authCustomer(authHelper.getLogin(), authHelper.getPassword());

        WebElement userProfile = authHelper.getDriver().findElement(
                By.xpath("//h3[contains(text(),'" + authHelper.getLogin() + "')]")
        );
        Assert.assertTrue(userProfile.getText().contains(authHelper.getLogin()));
    }

    @Test
    public void testAuthCustomerInvalidLogin() {
        String invalidLogin  = "incorrectUser123";
        String errorMessage = "Неверный Логин";

        authHelper.authCustomer(invalidLogin, authHelper.getPassword());

        WebElement userProfile = authHelper.getDriver().findElement(By.className("register_error"));
        Assert.assertTrue(userProfile.getText().contains(errorMessage));
    }

    @Test
    public void testAuthCustomerInvalidLoginPassword() {
        String errorMessage = "Неверный пароль";
        String invalidPassword = "123";

        authHelper.authCustomer(authHelper.getLogin(), invalidPassword);

        WebElement userProfile = authHelper.getDriver().findElement(By.className("register_error"));
        Assert.assertTrue(userProfile.getText().contains(errorMessage));
    }

    @After
    public void tearDown() {
        authHelper.closeBrowser();
    }
}