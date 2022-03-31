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
    AuthorizationPage authorizationPage = page(AuthorizationPage.class);
    RegistrationPage registrationPage;
    PersonalAccountPage personalAccountPage = page(PersonalAccountPage.class);
    RecoverPasswordPage recoverPasswordPage = page(RecoverPasswordPage.class);
    String token;

    @Before
    @Step("Регистрация пользователя")
    public void registation() {
        constructorPage = open(BASE_URL, ConstructorPage.class);
        constructorPage.getPersonalAccountButton().click();
        authorizationPage.getRegisterLink().click();
        registrationPage = page(RegistrationPage.class);
        registrationPage.registration();
        authorizationPage.getEntranceHeader().shouldBe(Condition.visible);
        authorizationPage.getConstructorButton().click();
    }


    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void logInToAccountMainSuccessTest() {
        constructorPage.getLogInToAccountButton().click();
        authorizationPage.getEntranceHeader().shouldBe(Condition.visible);
        authorizationPage.authorization(registrationPage.emailValue, registrationPage.passwordValue);

        constructorPage.getAssembleBurgerHeader().shouldBe(Condition.visible);
        assertThat(WebDriverRunner.getWebDriver().getCurrentUrl(), equalTo(constructorPage.url));
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void loginViaPersonalAccountButtonSuccessTest() {
        constructorPage.getPersonalAccountButton().click();
        authorizationPage.getEntranceHeader().shouldBe(Condition.visible);
        authorizationPage.authorization(registrationPage.emailValue, registrationPage.passwordValue);

        constructorPage.getAssembleBurgerHeader().shouldBe(Condition.visible);
        assertThat(WebDriverRunner.getWebDriver().getCurrentUrl(), equalTo(constructorPage.url));
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginViaButtonInRegistrationFormSuccessTest() {
        constructorPage.getPersonalAccountButton().click();
        authorizationPage.getRegisterLink().click();
        registrationPage.getLoginLink().click();
        authorizationPage.getEntranceHeader().shouldBe(Condition.visible);
        authorizationPage.authorization(registrationPage.emailValue, registrationPage.passwordValue);

        constructorPage.getAssembleBurgerHeader().shouldBe(Condition.visible);
        assertThat(WebDriverRunner.getWebDriver().getCurrentUrl(), equalTo(constructorPage.url));
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginViaButtonInPasswordRecoveryFormSuccessTest() {
        constructorPage.getPersonalAccountButton().click();
        authorizationPage.getRecoverPasswordLink().click();
        recoverPasswordPage.getLoginLink().click();
        authorizationPage.getEntranceHeader().shouldBe(Condition.visible);
        authorizationPage.authorization(registrationPage.emailValue, registrationPage.passwordValue);

        constructorPage.getAssembleBurgerHeader().shouldBe(Condition.visible);
        assertThat(WebDriverRunner.getWebDriver().getCurrentUrl(), equalTo(constructorPage.url));
    }


    @After()
    @Step("Выход из системы")
    public void tearDown() {
        authorizationPage.getConstructorButton().click();
        constructorPage.getPersonalAccountButton().click();
        personalAccountPage.getLogoutButton().click();
        authorizationPage.getConstructorButton().click();

        token = authorizationBack(registrationPage.emailValue, registrationPage.passwordValue);
        delete(token);
    }
}
