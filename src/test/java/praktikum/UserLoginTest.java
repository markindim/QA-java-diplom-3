package praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.constants.NamingButtonLogin;

import static org.junit.Assert.assertEquals;
import static praktikum.constants.NamingButtonLogin.*;

@RunWith(Parameterized.class)
public class UserLoginTest extends BaseTest {
    private final NamingButtonLogin namingButtonLogin;


    public UserLoginTest(NamingButtonLogin namingButtonLogin) {
        this.namingButtonLogin = namingButtonLogin;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {LOGIN_IN_HOME_PAGE},
                {LOGIN_IN_PERSONAL_ACCOUNT},
                {LOGIN_IN_REGISTER_PAGE},
                {LOGIN_IN_RECOVERY_PASSWORD}
        };
    }

    @Test
    @DisplayName("Авторизация пользователя")
    public void loginUserTest() {
        selectButton(namingButtonLogin);
        loginPage.waitLoadHeader();
        loginPage.setEmailField(user.getEmail());
        loginPage.setPasswordField(user.getPassword());
        loginPage.clickButtonLogin();
        homePage.waitLoadingLogoPage();
        String actual = webDriver.getCurrentUrl();
        assertEquals("https://stellarburgers.nomoreparties.site/", actual);
    }
}