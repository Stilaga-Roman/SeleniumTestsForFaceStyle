import com.codeborne.selenide.Configuration;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;



public class SelenideTetst {
    @Test
    public void testAuth() {
        open("https://qa-mesto.praktikum-services.ru/signin");
        $(byLinkText("Регистрация")).click();
        $(byId("email")).setValue("gogo@gogo.com");
        $(byId("password")).setValue("123456");
        $(byText("Зарегистрироваться")).click();
    }

    @Test
    public void testProverka() {
        open("https://qa-mesto.praktikum-services.ru");
        $(byId("email")).setValue("papa@papa.com");
        $(byId("password")).setValue("123");
        $(byClassName("auth-form__button")).click();
        sleep(8000);
        Integer countCards = $$(byText("Байкал")).size();
    }

    @Test
    public void testPhoto() {
        open("https://qa-mesto.praktikum-services.ru");
        $(byId("email")).setValue("papa@papa.com");
        $(byId("password")).setValue("123");
        $(byClassName("auth-form__button")).click();
        $(By.className("profile__image")).click();
        $(byId("owner-avatar")).setValue("https://code.s3.yandex.net/qa-automation-engineer/java/files/paid-track/sprint1/avatarSelenide.png");
        sleep(8000);
        $(byXpath(".//form[@name='edit-avatar']/button[text()='Сохранить']")).click();

    }

    @Test
    public void testCreateCard() {
        authCustomer();
        $(byClassName("profile__add-button")).click();
        $(byId("place-name")).setValue("Пага");
        $(byId("place-link")).setValue("https://code.s3.yandex.net/qa-automation-engineer/java/files/paid-track/sprint1/photoSelenium.jpg");
        $(byXpath(".//form[@name='new-card']/button[text()='Сохранить']")).click();
        sleep(8000);
    }

    @Test
    public void testCustomerInfo() {
        authCustomer();
        $(byClassName("profile__edit-button")).click();
        $(byId("owner-name")).setValue("Evgen");
        $(byId("owner-description")).setValue("mr.coffee");
        $(byXpath(".//form[@name='edit']/button[text()='Сохранить']")).click();
        sleep(8000);
    }

    @Test
    public void testSearchCurd() {
        authCustomer();
        String cartText = $$(byClassName("card")).get(2).find(byClassName("card__title")).getText();
    }


    @Test
    public void testMyDirectWebDriver() {
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        Configuration.browserCapabilities = options;

        // Запуск браузера и открытие сайта с использованием Selenide
        open("http://www.u164539.test-handyhost.ru/");
    }

    private void authCustomer() {
        open("https://qa-mesto.praktikum-services.ru");
        $(byId("email")).setValue("papa@papa.com");
        $(byId("password")).setValue("123");
        $(byClassName("auth-form__button")).click();
    }
}
