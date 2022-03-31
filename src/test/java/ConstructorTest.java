import com.codeborne.selenide.Condition;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import page.object.ConstructorPage;

import static com.codeborne.selenide.Selenide.open;
import static configuration.Configuration.BASE_URL;

public class ConstructorTest {

    ConstructorPage constructorPage;

    @Test
    @DisplayName("Конструктор. Переход к вкладке Соусы")
    public void tabTransitionsToSaucesInConstructorSuccessTest() {
        constructorPage = open(BASE_URL, ConstructorPage.class);

        constructorPage.getSaucesTab().click();
        constructorPage.getSausesHeader().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Конструктор. Переход к вкладке Начинки")
    public void tabTransitionsToFillingInConstructorSuccessTest() {
        constructorPage = open(BASE_URL, ConstructorPage.class);

        constructorPage.getFillingTab().click();
        constructorPage.getFillingHeader().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Конструктор. Переход к вкладке Булки")
    public void tabTransitionsToBurgersInConstructorSuccessTest() {
        constructorPage = open(BASE_URL, ConstructorPage.class);
        constructorPage.getSaucesTab().click();
        constructorPage.getSausesHeader().shouldBe(Condition.visible);

        constructorPage.getBurgersTab().click();
        constructorPage.getBurgersHeader().shouldBe(Condition.visible);
    }
}
