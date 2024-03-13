package praktikum.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage {
    private final WebDriver driver;
    private final By allFieldRegister = By.xpath(".//*[@class='text input__textfield text_type_main-default']");
    private final By buttonRegister = By.className("button_button__33qZ0");
    private final By incorrectPassword = By.className("input__error");
    private final By buttonLogin = By.className("Auth_link__1fOlj");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Получение поля для ввода имени")
    public WebElement getNameFieldRegister() {
        return driver.findElements(allFieldRegister).get(0);
    }

    @Step("Получение поля для ввода email")
    public WebElement getEmailFieldRegister() {
        return driver.findElements(allFieldRegister).get(1);
    }

    @Step("Получение поля для ввода password")
    public WebElement getPasswordFieldRegister() {
        return driver.findElements(allFieldRegister).get(2);
    }

    @Step("Ввод имени пользователя в поле")
    public void setNameRegister(String name) {
        getNameFieldRegister().sendKeys(name);
    }

    @Step("Ввод email пользователя в поле")
    public void setEmailRegister(String email) {
        getEmailFieldRegister().sendKeys(email);
    }

    @Step("Ввод пароля пользователя в поле")
    public void setPasswordRegister(String password) {
        getPasswordFieldRegister().sendKeys(password);
    }

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public void clickButtonRegister() {
        driver.findElement(buttonRegister).click();
    }

    @Step("Клик по кнопке 'Войти'")
    public void clickButtonLogin() {
        driver.findElement(buttonLogin).click();
    }

    @Step("Считывание ошибки при вводе некорректного пароля")
    public String getIncorrectPassword() {
        return driver.findElement(incorrectPassword).getText();
    }
}