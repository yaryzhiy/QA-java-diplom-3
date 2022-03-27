package page.object;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RecoverPasswordPage {

    //локатор линка Войти
    @FindBy(how = How.XPATH, using = "//a[text()='Войти']")
    public SelenideElement loginLink;
}
