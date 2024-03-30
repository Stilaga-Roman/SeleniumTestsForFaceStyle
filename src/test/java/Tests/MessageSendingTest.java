package Tests;

import Helpers.AuthHelper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.Date;
import java.util.List;

public class MessageSendingTest {

    private AuthHelper authHelper;

    @Before
    public void setUp() {
        authHelper = new AuthHelper();
        authHelper.authCustomer(authHelper.getLogin(), authHelper.getPassword());
    }

    @Test
    public void testMessageAppearance() {
        String uniqueMessage = "Тестовое сообщение " + new Date().getTime();
        sendMessage(uniqueMessage);

        WebElement messageElement = authHelper.getDriver().findElement(
                By.xpath("//div[a[text() = '" + uniqueMessage + "']]")
        );
        String displayedMessage = messageElement.getText();

        Assert.assertEquals(
                "Сообщение не соответствует отправленному", uniqueMessage, displayedMessage
        );
    }

    @Test
    public void testEmptyMessageNotAdded() {
        List<WebElement> messagesBeforeAttempt = getMessages();
        int beforeCount = messagesBeforeAttempt.size();

        sendMessage("");

        List<WebElement> messagesAfterAttempt = getMessages();
        int afterCount = messagesAfterAttempt.size();

        Assert.assertEquals(
                "Количество сообщений изменилось после попытки отправить пустое сообщение",
                beforeCount,
                afterCount
        );
    }

    private void sendMessage(String message) {
        WebElement messageInput = authHelper.getDriver().findElement(By.name("comment"));
        messageInput.sendKeys(message);

        WebElement submitButton = authHelper.getDriver().findElement(By.name("submit"));
        submitButton.click();
    }

    private List<WebElement> getMessages() {
        return authHelper.getDriver().findElements(
                By.xpath("//div[contains(@style, 'margin-left: 400px')]")
        );
    }

    @After
    public void tearDown() {
        authHelper.closeBrowser();
    }
}
