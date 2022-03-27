import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import page.object.AuthorizationPage;
import page.object.ConstructorPage;
import page.object.RegistrationPage;

import static com.UserOperations.authorizationBack;
import static com.UserOperations.delete;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static configuration.Configuration.BASE_URL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class RegistrationTest {

    @Test
    @DisplayName("Успешная регистрация пользователя")
    public void registrationHappyTest() {
        ConstructorPage constructorPage = open(BASE_URL, ConstructorPage.class);
        constructorPage.personalAccountButton.click();

        AuthorizationPage authorizationPage = page(AuthorizationPage.class);
        authorizationPage.registerLink.click();

        RegistrationPage registrationPage = page(RegistrationPage.class);
        registrationPage.registration();

        authorizationPage.registerLink.shouldBe(Condition.visible);
        assertThat(WebDriverRunner.getWebDriver().getCurrentUrl(), equalTo(authorizationPage.url));

        String token = authorizationBack(registrationPage.emailValue, registrationPage.passwordValue);
        delete(token);
    }

    @Test
    @DisplayName("Ошибка регистрации с невалидным паролем")
    public void registrationWrongPasswordErrorTest() {
        ConstructorPage constructorPage = open(BASE_URL, ConstructorPage.class);
        constructorPage.personalAccountButton.click();

        AuthorizationPage authorizationPage = page(AuthorizationPage.class);
        authorizationPage.registerLink.click();

        RegistrationPage registrationPage = page(RegistrationPage.class);
        registrationPage.registration("pass");

        registrationPage.incorrectPasswordWarning.shouldBe(Condition.visible);
    }
}
