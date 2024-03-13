package praktikum;

import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import praktikum.constants.NameButtonLogin;
import praktikum.model.User;
import praktikum.pageobjects.*;
import praktikum.service.UserGenerator;

import static io.restassured.RestAssured.given;
import static praktikum.constants.Endpoints.*;

public class BaseTest {
    protected WebDriver webDriver;
    protected User user;
    protected UserGenerator userGenerator;
    protected RegisterPage registerPage;
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected UserPage userPage;
    private RecoveryPasswordPage recoveryPasswordPage;

    @Before
    public void setUp() {
        webDriver = WebDriverFactory.getWebDriver();
        webDriver.get(BASE_URI);
        registerPage = new RegisterPage(webDriver);
        loginPage = new LoginPage(webDriver);
        homePage = new HomePage(webDriver);
        userPage = new UserPage(webDriver);
        recoveryPasswordPage = new RecoveryPasswordPage(webDriver);
        createUser();
    }

    @After
    @DisplayName("Удаление пользователя и закрытие браузера")
    public void tearDown() {
        deleteUser();
        webDriver.quit();
    }

    private void createUser() {
        userGenerator = new UserGenerator();
        user = userGenerator.getRandomUser();
        given().filter(new AllureRestAssured()).contentType(ContentType.JSON).body(user).post(BASE_URI + ENDPOINT_FOR_CREATE_USER);
    }

    private void deleteUser() {
        String accessToken = given().filter(new AllureRestAssured()).contentType(ContentType.JSON).body(user).post(BASE_URI + ENDPOINT_FOR_LOGIN_USER).body().path("accessToken");
        if (accessToken != null)
            given().filter(new AllureRestAssured()).contentType(ContentType.JSON).header("Authorization", accessToken).body(user).delete(BASE_URI + ENDPOINT_FOR_DELETE_USER);
    }

    public void selectButton(NameButtonLogin nameButtonLogin) {
        switch (nameButtonLogin) {
            case LOGIN_IN_HOME_PAGE:
                homePage.clickButtonEnter();
                break;
            case LOGIN_IN_PERSONAL_ACCOUNT:
                homePage.clickPersonalAccount();
                break;
            case LOGIN_IN_REGISTER_PAGE:
                homePage.clickPersonalAccount();
                loginPage.clickButtonRegister();
                registerPage.clickButtonLogin();
                break;
            case LOGIN_IN_RECOVERY_PASSWORD:
                homePage.clickPersonalAccount();
                loginPage.clickButtonRecoveryPassword();
                recoveryPasswordPage.clickButtonLogin();
                break;
        }
    }
}