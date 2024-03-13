package praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.constants.SectionName;
import praktikum.pageobjects.*;

import static org.junit.Assert.assertTrue;
import static praktikum.constants.Endpoints.BASE_URI;
import static praktikum.constants.SectionName.*;

@RunWith(Parameterized.class)
public class ConstructorTest extends BaseTest {
    private final SectionName sectionName;

    @Override
    @Before
    public void setUp() {
        driver = WebDriverFactory.getWebDriver();
        driver.get(BASE_URI);
        homePage = new HomePage(driver);
    }

    @Override
    @After
    public void tearDown() {
        driver.quit();
    }

    public ConstructorTest(SectionName sectionName) {
        this.sectionName = sectionName;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {BUN},
                {SAUCE},
                {FILLING}
        };
    }

    @Test
    @DisplayName("Переключение вкладок на странице Конструктора")
    public void checkSectionTest() {
        homePage.clickSection(sectionName);
        assertTrue(homePage.getSection(sectionName));
    }
}