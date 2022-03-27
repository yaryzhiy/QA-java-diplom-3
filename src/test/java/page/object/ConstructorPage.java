package page.object;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ConstructorPage {

    //локатор кнопки Личный кабинет
    @FindBy(how = How.XPATH, using = "//p[text()='Личный Кабинет']")
    public SelenideElement personalAccountButton;

    //локатор заголовка Соберите бургер
    @FindBy(how = How.XPATH, using = "//h1[text()='Соберите бургер']")
    public SelenideElement assembleBurgerHeader;

    //локатор вкладки Булки
    @FindBy(how = How.XPATH, using = "//span[text()='Булки']")
    public SelenideElement burgersTab;

    //локатор вкладки Соусы
    @FindBy(how = How.XPATH, using = "//span[text()='Соусы']")
    public SelenideElement saucesTab;

    //локатор вкладки Начинки
    @FindBy(how = How.XPATH, using = "//span[text()='Начинки']")
    public SelenideElement fillingTab;

    //локатор заголовка Булки
    @FindBy(how = How.XPATH, using = "//h2[text()='Булки']")
    public SelenideElement burgersHeader;

    //локатор заголовка Соусы
    @FindBy(how = How.XPATH, using = "//h2[text()='Соусы']")
    public SelenideElement sausesHeader;

    //локатор заголовка Начинки
    @FindBy(how = How.XPATH, using = "//h2[text()='Начинки']")
    public SelenideElement fillingHeader;

    //локатор кнопки Войти в аккаунт
    @FindBy(how = How.XPATH, using = "//button[text()='Войти в аккаунт']")
    public SelenideElement logInToAccountButton;


    public final String url = "https://stellarburgers.nomoreparties.site/";
}
