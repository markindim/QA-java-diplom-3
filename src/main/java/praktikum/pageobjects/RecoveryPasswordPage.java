package praktikum.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecoveryPasswordPage {
    private final WebDriver webDriver;
    private final By buttonLogin = By.className("Auth_link__1fOlj");

    public RecoveryPasswordPage(WebDriver driver) {
        this.webDriver = driver;
    }

    @Step("Клик по кнопке 'Войти'")
    public void clickButtonLogin() {
        webDriver.findElement(buttonLogin).click();
    }
}