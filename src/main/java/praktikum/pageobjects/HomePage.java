package praktikum.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.constants.SectionName;

import java.time.Duration;

public class HomePage {
    private final WebDriver driver;

    private final By personalAccount = By.xpath(".//p[text() = 'Личный Кабинет']");
    private final By buttonEnter = By.className("button_button__33qZ0");
    private final By logoPage = By.xpath(".//*[@class='active']");
    private final By sectionBun = By.xpath(".//span[text()='Булки']/..");
    private final By sectionSauce = By.xpath(".//span[text()='Соусы']/..");
    private final By sectionFilling = By.xpath(".//span[text()='Начинки']/..");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitLoadLogoPage() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(driver -> (driver.findElement(logoPage)).isEnabled());
    }

    @Step("Клик по кнопке 'Войти в аккаунт' на главной странице")
    public void clickButtonEnter() {
        waitLoadLogoPage();
        driver.findElement(buttonEnter).click();
    }

    @Step("Клик по кнопке 'Личный кабинет' на главной странице")
    public void clickPersonalAccount() {
        waitLoadLogoPage();
        driver.findElement(personalAccount).click();
    }

    @Step("Клик по одной из секций 'Соусы', 'Начинки' или 'Булки' на главной странице")
    public void clickSection(SectionName sectionName) {
        switch (sectionName) {
            case BUN:
                driver.findElement(sectionFilling).click();
                driver.findElement(sectionBun).click();
                break;
            case SAUCE:

                driver.findElement(sectionSauce).click();
                break;
            case FILLING:
                ;
                driver.findElement(sectionFilling).click();
                break;
            default:
                throw new IllegalStateException("Элемент не найден: " + sectionName);
        }
    }

    public boolean getSection(SectionName sectionName) {
        switch (sectionName) {
            case BUN:
                return driver.findElement(sectionBun).getAttribute("class").contains("current");
            case SAUCE:
                return driver.findElement(sectionSauce).getAttribute("class").contains("current");
            case FILLING:
                return driver.findElement(sectionFilling).getAttribute("class").contains("current");
            default:
                throw new RuntimeException("Элемент не найден:" + sectionName);
        }
    }
}