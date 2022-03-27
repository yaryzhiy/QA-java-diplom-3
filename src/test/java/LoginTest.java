import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import page.object.*;

import static com.UserOperations.authorizationBack;
import static com.UserOperations.delete;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static configuration.Configuration.BASE_URL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

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
        authorizationPage.authorization(registrationPage.emailValue, registrationPage.passwordValue);
        constructorPage.assembleBurgerHeader.shouldBe(Condition.visible);
        assertThat(WebDriverRunner.getWebDriver().getCurrentUrl(), equalTo(constructorPage.url));

        logout();
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void loginViaPersonalAccountButtonSuccessTest() {
        constructorPage.personalAccountButton.click();

        authorizationPage.entranceHeader.shouldBe(Condition.visible);
        authorizationPage.authorization(registrationPage.emailValue, registrationPage.passwordValue);
        constructorPage.assembleBurgerHeader.shouldBe(Condition.visible);
        assertThat(WebDriverRunner.getWebDriver().getCurrentUrl(), equalTo(constructorPage.url));

        logout();
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginViaButtonInRegistrationFormSuccessTest() {
        constructorPage.personalAccountButton.click();
        authorizationPage.registerLink.click();
        registrationPage.loginLink.click();

        authorizationPage.entranceHeader.shouldBe(Condition.visible);
        authorizationPage.authorization(registrationPage.emailValue, registrationPage.passwordValue);
        constructorPage.assembleBurgerHeader.shouldBe(Condition.visible);
        assertThat(WebDriverRunner.getWebDriver().getCurrentUrl(), equalTo(constructorPage.url));

        logout();
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginViaButtonInPasswordRecoveryFormSuccessTest() {
        constructorPage.personalAccountButton.click();
        authorizationPage.recoverPasswordLink.click();
        RecoverPasswordPage recoverPasswordPage = page(RecoverPasswordPage.class);
        recoverPasswordPage.loginLink.click();

        authorizationPage.entranceHeader.shouldBe(Condition.visible);
        authorizationPage.authorization(registrationPage.emailValue, registrationPage.passwordValue);
        constructorPage.assembleBurgerHeader.shouldBe(Condition.visible);
        assertThat(WebDriverRunner.getWebDriver().getCurrentUrl(), equalTo(constructorPage.url));

        logout();
    }


    @Step("Выход из системы")
    public void logout() {
        authorizationPage.constructorButton.click();
        constructorPage.personalAccountButton.click();
        personalAccountPage.logoutButton.click();
        authorizationPage.constructorButton.click();
    }

    @After
    public void down() {
        token = authorizationBack(registrationPage.emailValue, registrationPage.passwordValue);
        delete(token);
    }


}
