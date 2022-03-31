package page.object;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

@Data
public class RecoverPasswordPage {

    //локатор линка Войти
    @FindBy(how = How.XPATH, using = "//a[text()='Войти']")
    private SelenideElement loginLink;
}
