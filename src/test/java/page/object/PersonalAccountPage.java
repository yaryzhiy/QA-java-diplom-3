package page.object;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PersonalAccountPage {

    //локатор кнопки Конструктор
    @FindBy(how = How.XPATH, using = "//p[text()='Конструктор']")
    public SelenideElement constructorButton;

    //локатор логотипа Stellar Burgers
    @FindBy(how = How.XPATH, using = "//a[@href='/'][1]")
    public SelenideElement stellarBurgersLogo;

    //локатор кнопки Профиль
    @FindBy(how = How.XPATH, using = "//a[text()='Профиль']")
    public SelenideElement profileButton;

    //локатор кнопки Выход
    @FindBy(how = How.XPATH, using = "//button[text()='Выход']")
    public SelenideElement logoutButton;


    public final String url = "https://stellarburgers.nomoreparties.site/account/profile";
}
