package praktikum.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.constants.SectionName;

import java.time.Duration;

public class HomePage {
    private final WebDriver webDriver;

    private final By personalAccount = By.xpath(".//p[text() = 'Личный Кабинет']");
    private final By buttonEnter = By.className("button_button__33qZ0");
    private final By logoPage = By.xpath(".//*[@class='active']");
    public final By sectionSauce = By.xpath(".//*[text()='Соусы']//parent::div");
    public final By sectionFilling = By.xpath(".//*[text()='Начинки']//parent::div");
    public final By sectionBun = By.xpath(".//*[text()='Булки']//parent::div");

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

    @Step("Клик по кнопке 'Соусы' на главной странице")
    public void clickSectionSauce() {
        waitLoadingLogoPage();
        webDriver.findElement(sectionSauce).click();
    }

    @Step("Клик по кнопке 'Начинки' на главной странице")
    public void clickSectionFilling() {
        waitLoadingLogoPage();
        webDriver.findElement(sectionFilling).click();
    }

    @Step("Клик по кнопке 'Булки' на главной странице")
    public void clickSectionBun() {
        clickSectionSauce();
        clickSectionFilling();
        webDriver.findElement(sectionBun).click();
    }

    @Step("Клик по одной из секций 'Соусы', 'Начинки', 'Булки' на главной странице")
    public void clickSection(SectionName sectionName) {
        switch (sectionName) {
            case BUN:
                clickSectionBun();
                break;
            case SAUCE:
                clickSectionSauce();
                break;
            case FILLING:
                clickSectionFilling();
                break;
        }
    }

    public WebElement getSection(SectionName sectionName) {
        switch (sectionName) {
            case BUN:
                return webDriver.findElement(sectionBun);
            case SAUCE:
                return webDriver.findElement(sectionSauce);
            case FILLING:
                return webDriver.findElement(sectionFilling);
            default:
                throw new RuntimeException("Некорректное название элемента");
        }
    }

    public String getClassName(SectionName sectionName) {
        return getSection(sectionName).getText();
    }
}