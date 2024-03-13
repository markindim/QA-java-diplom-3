package praktikum.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;

    private final By logoHeader = By.xpath(".//h2[text() = 'Вход']");
    private final By buttonLogin = By.xpath(".//button[text() = 'Войти']");
    private final By buttonRegister = By.xpath(".//*[text() = 'Зарегистрироваться']");
    private final By buttonRecoveryPassword = By.xpath(".//*[text() = 'Восстановить пароль']");
    private final By allFieldLogin = By.xpath(".//*[@class='text input__textfield text_type_main-default']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitLoadHeader() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(driver -> (driver.findElement(logoHeader)).getText() != null
                && !driver.findElement(logoHeader).getText().isEmpty());
    }

    @Step("Клик по кнопке 'Войти'")
    public void clickButtonLogin() {
        driver.findElement(buttonLogin).click();
    }

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public void clickButtonRegister() {
        driver.findElement(buttonRegister).click();
    }

    @Step("Клик по кнопке 'Восстановить пароль'")
    public void clickButtonRecoveryPassword() {
        driver.findElement(buttonRecoveryPassword).click();
    }

    public WebElement getEmailField() {
        return driver.findElements(allFieldLogin).get(0);
    }

    public WebElement getPasswordField() {
        return driver.findElements(allFieldLogin).get(1);
    }

    public void setEmailField(String email) {
        getEmailField().sendKeys(email);
    }

    public void setPasswordField(String password) {
        getPasswordField().sendKeys(password);
    }
}