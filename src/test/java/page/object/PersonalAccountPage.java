package page.object;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PersonalAccountPage {

    //локатор кнопки Выход
    @FindBy(how = How.XPATH, using = "//button[text()='Выход']")
    public SelenideElement logoutButton;
}
