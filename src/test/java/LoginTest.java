import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import page.object.AuthorizationPage;
import page.object.ConstructorPage;
import page.object.PersonalAccountPage;
import page.object.RegistrationPage;

import static com.UserOperations.authorization;
import static com.UserOperations.delete;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static configuration.Configuration.BASE_URL;

public class LoginTest {

    ConstructorPage constructorPage;
    AuthorizationPage authorizationPage;
    RegistrationPage registrationPage;
    PersonalAccountPage personalAccountPage = page(PersonalAccountPage.class);
    String token;

    @Before
    @Step("Регистрация пользователя")
    public void registation() {
        constructorPage = open(BASE_URL, ConstructorPage.class);
        constructorPage.personalAccountButton.click();

        authorizationPage = page(AuthorizationPage.class);
        authorizationPage.registerLink.click();

        registrationPage = page(RegistrationPage.class);
        registrationPage.registration();
        authorizationPage.entranceHeader.shouldBe(Condition.visible);
        authorizationPage.constructorButton.click();
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void logInToAccountMainSuccessTest() {
        constructorPage.logInToAccountButton.click();

        authorizationPage.entranceHeader.shouldBe(Condition.visible);
        token = authorization(registrationPage.emailValue, registrationPage.passwordValue);

        logout();
    }

    @Step("Выход из системы")
    public void logout() {
        authorizationPage.constructorButton.click();
        constructorPage.assembleBurgerHeader.shouldBe(Condition.visible);
        constructorPage.personalAccountButton.click();
        personalAccountPage.logoutButton.shouldBe(Condition.visible);
        personalAccountPage.logoutButton.click();
        authorizationPage.entranceHeader.shouldBe(Condition.visible);
        authorizationPage.constructorButton.click();
    }

    @After
    public void down() {
        delete(token);
    }


}
