package Tests;

import Helpers.AuthHelper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class UserSelectionAndViewTest {

    private AuthHelper authHelper;

    @Before
    public void setUp() {
        authHelper = new AuthHelper();
        authHelper.authCustomer(authHelper.getLogin(), authHelper.getPassword());
    }

    @Test
    public void testUserNavigationAndNameDisplay() {
        String userName = "Стилягин Роман Петрович (admin)";

        authHelper.getDriver().findElement(By.linkText("Пользователи сайта")).click();
        authHelper.getDriver().findElement(By.linkText(userName)).click();

        String actualUserName  = authHelper.getDriver().findElement(By.xpath("//h3[1]")).getText();
        Assert.assertEquals(
                "Имя пользователя на странице профиля не соответствует ожидаемому",
                userName,
                actualUserName
        );
    }

    @After
    public void tearDown() {
        authHelper.closeBrowser();
    }


}
