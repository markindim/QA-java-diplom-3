package praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.constants.SectionName;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static praktikum.constants.SectionName.*;

@RunWith(Parameterized.class)
public class ConstructorTest extends BaseTest {
    private final SectionName sectionName;
    private final List<String> ingredients = Arrays.asList("Булки", "Соусы", "Начинки");

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

        assertTrue(ingredients.contains(homePage.getClassName(sectionName)));
    }
}