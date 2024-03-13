package praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import praktikum.pageobjects.HomePage;
import praktikum.pageobjects.LoginPage;
import praktikum.pageobjects.RegisterPage;
import praktikum.service.UserGenerator;

import static org.junit.Assert.assertEquals;

public class UserRegistrationTest extends BaseTest {

    @Override
    @Before
    @DisplayName("Создание драйвера, открытие браузера и подготовка тестовых данных для регистрации пользователя")
    public void setUp() {
        driver = WebDriverFactory.getWebDriver();
        driver.get("https://stellarburgers.nomoreparties.site/register");
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        userGenerator = new UserGenerator();
        user = userGenerator.getRandomUser();
        registerPage = new RegisterPage(driver);
    }

    @Test
    @Description("Позитивный тест регистрации пользователя с валидными данными")
    public void registerValidUser() {
        registerPage.setNameRegister(user.getName());
        registerPage.setEmailRegister(user.getEmail());
        registerPage.setPasswordRegister(user.getPassword());
        registerPage.clickButtonRegister();
        loginPage.waitLoadHeader();

        assertEquals("https://stellarburgers.nomoreparties.site/login", driver.getCurrentUrl());
    }

    @Test
    @Description("Негативный тест регистрации пользователя с невалидными данными - пароль меньше 6 символов")
    public void registerInvalidUser() {
        registerPage.setNameRegister(user.getName());
        registerPage.setEmailRegister(user.getEmail());
        registerPage.setPasswordRegister(userGenerator.getInvalidPassword());
        registerPage.clickButtonRegister();

        assertEquals("Некорректный пароль", registerPage.getIncorrectPassword());
    }
}