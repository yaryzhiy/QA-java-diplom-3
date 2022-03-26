package page.object;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AuthorizationPage {

    //локатор кнопки Конструктор
    @FindBy(how = How.XPATH, using = "//p[text()='Конструктор']")
    public SelenideElement constructorButton;

    //локатор заголовка Вход
    @FindBy(how = How.XPATH, using = "//h2[text()='Вход']")
    public SelenideElement entranceHeader;

    //локатор поля ввода Email
    @FindBy(how = How.XPATH, using = "//div/label[text()='Email']/following::input[1]")
    public SelenideElement emailInput;

    //локатор поля ввода Пароль
    @FindBy(how = How.XPATH, using = "//div/label[text()='Пароль']/following::input[1]")
    public SelenideElement passwordInput;

    //локатор линка Зарегистрироваться
    @FindBy(how = How.XPATH, using = "//a[text()='Зарегистрироваться']")
    public SelenideElement registerLink;


    public final String url = "https://stellarburgers.nomoreparties.site/login";
}
