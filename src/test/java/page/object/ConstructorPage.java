package page.object;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ConstructorPage {

    //локатор кнопки Личный кабинет
    @FindBy(how = How.XPATH, using = "//p[text()='Личный Кабинет']")
    public SelenideElement personalAccountButton;
}
