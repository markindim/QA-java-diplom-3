package praktikum.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.constants.NameButtonConstructor;
import praktikum.model.User;

import java.time.Duration;

public class UserPage {
    public final WebDriver webDriver;
    private final By allUserFields = By.xpath(".//*[@class='text input__textfield text_type_main-default input__textfield-disabled']");
    private final By buttonProfile = By.xpath(".//*[text()='Профиль']");
    private final By buttonLogout = By.xpath(".//*[text()='Выход']");
    private final By buttonConstructor = By.xpath(".//*[text()='Конструктор']");
    private final By logoStellarBurger = By.xpath(".//*[@class='AppHeader_header__logo__2D0X2']");


    public UserPage(WebDriver driver) {
        this.webDriver = driver;
    }

    public void waitLoadingPage() {
        new WebDriverWait(webDriver, Duration.ofSeconds(3)).until(driver -> (driver.findElement(buttonProfile)).isEnabled());
    }

    @Step("Получение поля с именем пользователя")
    public String getUserName() {
        return webDriver.findElements(allUserFields).get(0).getAttribute("value");
    }

    @Step("Получение поля с логином пользователя")
    public String getUserLogin() {
        return webDriver.findElements(allUserFields).get(1).getAttribute("value");
    }

    @Step("Клик по кнопке 'Конструктор'")
    public void clickButtonConstructor() {
        webDriver.findElement(buttonConstructor).click();
    }

    @Step("Клик по логотопу")
    public void clickLogoStellarBurger() {
        webDriver.findElement(logoStellarBurger).click();
    }

    @Step("Клик по кнопке 'Выход'")
    public void clickButtonLogout() {
        webDriver.findElement(buttonLogout).click();
    }

    public void checkButton(NameButtonConstructor buttonName) {
        switch (buttonName) {
            case CONSTRUCTOR:
                clickButtonConstructor();
                break;
            case LOGO_STELLAR_BURGER:
                clickLogoStellarBurger();
                break;
        }
    }

    @Step("Переход в личный кабинет пользователя")
    public void goToPersonalAccount(HomePage homePage, LoginPage loginPage, User user) {
        homePage.clickPersonalAccount();
        loginPage.waitLoadHeader();
        loginPage.setEmailField(user.getEmail());
        loginPage.setPasswordField(user.getPassword());
        loginPage.clickButtonLogin();
        homePage.clickPersonalAccount();
    }
}