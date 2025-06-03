package site.stellarburgers.model;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.Objects;

public class LoginPage extends Header {

    public final static String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";

    @FindBy(how = How.XPATH, using = "//a[text()='Зарегистрироваться']")
    private SelenideElement REGISTER_LINK;

    @FindBy(how = How.XPATH, using = "//input[@name='name']")
    private SelenideElement EMAIL_INPUT_FIELD;

    @FindBy(how = How.XPATH, using = "//input[@name='Пароль']")
    private SelenideElement PASSWORD_INPUT_FIELD;

    @FindBy(how = How.XPATH, using = "//button[text()='Войти']")
    private SelenideElement SIGN_IN_BUTTON;

    @FindBy(how = How.XPATH, using = "//a[text()='Восстановить пароль']")
    private SelenideElement PASSWORD_RECOVERY_LINK;

    @Step("Кликнуть на ссылку 'Зарегистрироваться'")
    public void clickRegisterLink() {
        REGISTER_LINK.click();
    }

    @Step("Ввести email: {email}")
    public void setEmail(String email) {
        while (!Objects.equals(EMAIL_INPUT_FIELD.getValue(), email)) {
            EMAIL_INPUT_FIELD.shouldBe(Condition.editable).setValue(email);
        }
    }

    @Step("Ввести пароль")
    public void setPassword(String password) {
        PASSWORD_INPUT_FIELD.setValue(password);
    }

    @Step("Кликнуть кнопку 'Войти'")
    public void clickSignInButton() {
        SIGN_IN_BUTTON.click();
    }

    @Step("Логин пользователя с email: {email}")
    public void login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickSignInButton();
    }

    @Step("Кликнуть на ссылку 'Восстановить пароль'")
    public void clickPasswordRecoveryLink() {
        PASSWORD_RECOVERY_LINK.click();
    }
}
