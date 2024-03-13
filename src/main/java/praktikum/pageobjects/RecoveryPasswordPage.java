package praktikum.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecoveryPasswordPage {
    private final WebDriver driver;
    private final By buttonLogin = By.className("Auth_link__1fOlj");

    public RecoveryPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик по кнопке 'Войти'")
    public void clickButtonLogin() {
        driver.findElement(buttonLogin).click();
    }
}