package page.object;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

@Data
public class PersonalAccountPage {

    //локатор кнопки Конструктор
    @FindBy(how = How.XPATH, using = "//p[text()='Конструктор']")
    private SelenideElement constructorButton;

    //локатор логотипа Stellar Burgers
    @FindBy(how = How.XPATH, using = "//a[@href='/'][1]")
    private SelenideElement stellarBurgersLogo;

    //локатор кнопки Профиль
    @FindBy(how = How.XPATH, using = "//a[text()='Профиль']")
    private SelenideElement profileButton;

    //локатор кнопки Выход
    @FindBy(how = How.XPATH, using = "//button[text()='Выход']")
    private SelenideElement logoutButton;


    public final String url = "https://stellarburgers.nomoreparties.site/account/profile";
}
