package praktikum.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.constants.SectionName;

import java.time.Duration;

public class HomePage {
    private final WebDriver webDriver;

    private final By personalAccount = By.xpath(".//p[text() = 'Личный Кабинет']");
    private final By buttonEnter = By.className("button_button__33qZ0");
    private final By logoPage = By.xpath(".//*[@class='active']");
    private final By sectionBun = By.xpath("//span[text()='Булки']/..");
    private final By sectionSauce = By.xpath("//span[text()='Соусы']/..");
    private final By sectionFilling = By.xpath("//span[text()='Начинки']/..");

    public HomePage(WebDriver driver) {
        this.webDriver = driver;
    }

    public void waitLoadingLogoPage() {
        new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(driver -> (driver.findElement(logoPage)).isEnabled());
    }

    @Step("Клик по кнопке 'Войти в аккаунт' на главной странице")
    public void clickButtonEnter() {
        waitLoadingLogoPage();
        webDriver.findElement(buttonEnter).click();
    }

    @Step("Клик по кнопке 'Личный кабинет' на главной странице")
    public void clickPersonalAccount() {
        waitLoadingLogoPage();
        webDriver.findElement(personalAccount).click();
    }

    @Step("Клик по одной из секций 'Соусы', 'Начинки' или 'Булки' на главной странице")
    public void clickSection(SectionName sectionName) {
        switch (sectionName) {
            case BUN:
                waitLoadingLogoPage();
                webDriver.findElement(sectionFilling).click();
                webDriver.findElement(sectionBun).click();
                break;
            case SAUCE:
                waitLoadingLogoPage();
                webDriver.findElement(sectionSauce).click();
                break;
            case FILLING:
                waitLoadingLogoPage();
                webDriver.findElement(sectionFilling).click();
                break;
        }
    }

    public boolean getSection(SectionName sectionName) {
        switch (sectionName) {
            case BUN:
                return webDriver.findElement(sectionBun).getAttribute("class").contains("current");
            case SAUCE:
                return webDriver.findElement(sectionSauce).getAttribute("class").contains("current");
            case FILLING:
                return webDriver.findElement(sectionFilling).getAttribute("class").contains("current");
            default:
                throw new RuntimeException("Элемент не найден");
        }
    }
}