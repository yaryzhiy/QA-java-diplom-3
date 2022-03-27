import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import page.object.ConstructorPage;

import static com.codeborne.selenide.Selenide.open;
import static configuration.Configuration.BASE_URL;

public class ConstructorTest {

    @Test
    @DisplayName("Конструктор. Переходы по вкладкам")
    @Description("Раздел «Конструктор». Проверка работы переходов к разделам: «Булки», «Соусы», «Начинки»")
    public void tabTransitionsInConstructorSuccessTest() {
        ConstructorPage constructorPage = open(BASE_URL, ConstructorPage.class);

        constructorPage.saucesTab.click();
        constructorPage.sausesHeader.shouldBe(Condition.visible);

        constructorPage.fillingTab.click();
        constructorPage.fillingHeader.shouldBe(Condition.visible);

        constructorPage.burgersTab.click();
        constructorPage.burgersHeader.shouldBe(Condition.visible);
    }
}
