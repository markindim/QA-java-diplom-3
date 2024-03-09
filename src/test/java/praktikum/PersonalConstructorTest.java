package praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.constants.NamingButtonConstructor;

import static org.junit.Assert.assertEquals;
import static praktikum.constants.NamingButtonConstructor.*;

@RunWith(Parameterized.class)
public class PersonalConstructorTest extends BaseTest {
    private final NamingButtonConstructor namingButtonConstructor;

    public PersonalConstructorTest(NamingButtonConstructor namingButtonConstructor) {
        this.namingButtonConstructor = namingButtonConstructor;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {CONSTRUCTOR},
                {LOGO_STELLAR_BURGER}
        };
    }

    @Test
    @DisplayName("Переход из Личного кабинета в Конструктор")
    public void toConstructorFromPersonalAccount() {
        userPage.goToPersonalAccount(homePage, loginPage, user);
        userPage.waitLoadingPage();
        userPage.clickButtonConstructor();

        assertEquals("https://stellarburgers.nomoreparties.site/", webDriver.getCurrentUrl());
    }
}