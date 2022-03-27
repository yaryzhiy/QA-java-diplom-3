import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import page.object.AuthorizationPage;
import page.object.ConstructorPage;
import page.object.PersonalAccountPage;
import page.object.RegistrationPage;

import static com.UserOperations.authorizationBack;
import static com.UserOperations.delete;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static configuration.Configuration.BASE_URL;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PersonalAccountTest {

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
        authorizationPage.authorization(registrationPage.emailValue, registrationPage.passwordValue);
    }


    @Test
    @DisplayName("Переход в личный кабинет")
    public void transferToPersonalAccountSuccessTest() {
        constructorPage.personalAccountButton.click();

        personalAccountPage.profileButton.shouldBe(Condition.visible);
        assertThat(WebDriverRunner.getWebDriver().getCurrentUrl(), equalTo(personalAccountPage.url));

        logout();
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на «Конструктор»")
    public void switchingFromPersonalAccountToConstructorViaConstructorButtonSuccessTest() {
        constructorPage.personalAccountButton.click();
        personalAccountPage.constructorButton.click();

        constructorPage.assembleBurgerHeader.shouldBe(Condition.visible);
        assertThat(WebDriverRunner.getWebDriver().getCurrentUrl(), equalTo(constructorPage.url));

        logout();
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    public void switchingFromPersonalAccountToConstructorViaLogoSuccessTest() {
        constructorPage.personalAccountButton.click();
        personalAccountPage.stellarBurgersLogo.click();

        constructorPage.assembleBurgerHeader.shouldBe(Condition.visible);
        assertThat(WebDriverRunner.getWebDriver().getCurrentUrl(), equalTo(constructorPage.url));

        logout();
    }

    @Test
    @DisplayName("Выход из аккаунта")
    public void logoutFromAccountSuccessTest() {
        constructorPage.personalAccountButton.click();
        personalAccountPage.logoutButton.click();

        authorizationPage.entranceHeader.shouldBe(Condition.visible);
        assertThat(WebDriverRunner.getWebDriver().getCurrentUrl(), equalTo(authorizationPage.url));
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
