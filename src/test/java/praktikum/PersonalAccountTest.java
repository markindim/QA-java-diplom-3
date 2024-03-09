package praktikum;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PersonalAccountTest extends BaseTest {

    @Step("Авторизация пользователя")
    public void loginPersonalAccount() {
        homePage.clickPersonalAccount();
        loginPage.waitLoadHeader();
        loginPage.setEmailField(user.getEmail());
        loginPage.setPasswordField(user.getPassword());
        loginPage.clickButtonLogin();
    }

    @Step("Переход в Личный кабинет после авторизации")
    public void toPersonalAccountAfterLogin() {
        homePage.clickPersonalAccount();
        userPage.waitLoadingPage();
    }


    @Test
    @DisplayName("Переход  пользователя в Личный кабинет")
    public void toPersonalAccountWithAuthUser() {
        loginPersonalAccount();
        homePage.clickPersonalAccount();
        userPage.waitLoadingPage();

        assertEquals(user.getName(), userPage.getUserName());
        assertEquals(user.getEmail(), userPage.getUserLogin());
    }

    @Test
    @DisplayName("Выход из Личного кабинета")
    public void logoutUserFromPersonalAccount() {
        loginPersonalAccount();
        toPersonalAccountAfterLogin();
        userPage.clickButtonLogout();
        loginPage.waitLoadHeader();

        assertEquals("https://stellarburgers.nomoreparties.site/login", webDriver.getCurrentUrl());
    }
}