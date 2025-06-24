package site.stellarburgers.model;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegistrationPage extends Header {

    @FindBy(how = How.XPATH, using = "//div[label[text()='Имя']]/input")
    private SelenideElement NAME_INPUT_FIELD;

    @FindBy(how = How.XPATH, using = "//div[label[text()='Email']]/input")
    private SelenideElement EMAIL_INPUT_FIELD;

    @FindBy(how = How.XPATH, using = "//input[@name='Пароль']")
    private SelenideElement PASSWORD_INPUT_FIELD;

    @FindBy(how = How.XPATH, using = "//button[text()='Зарегистрироваться']")
    private SelenideElement REGISTER_BUTTON;

    @FindBy(how = How.XPATH, using = "//p[text()='Некорректный пароль']")
    private SelenideElement INCORRECT_PASSWORD_TEXT;

    @FindBy(how = How.XPATH, using = "//a[@href='/account']")
    private SelenideElement SIGN_IN_LINK;

    @Step("Ввести имя: {name}")
    public void setName(String name) {
        NAME_INPUT_FIELD.setValue(name);
    }

    @Step("Ввести email: {email}")
    public void setEmail(String email) {
        EMAIL_INPUT_FIELD.setValue(email);
    }

    @Step("Ввести пароль")
    public void setPassword(String password) {
        PASSWORD_INPUT_FIELD.setValue(password);
    }

    @Step("Кликнуть кнопку 'Зарегистрироваться'")
    public void clickRegisterButton() {
        REGISTER_BUTTON.click();
    }

    @Step("Регистрация пользователя с именем: {name}, email: {email}")
    public void register(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
    }

    @Step("Проверить видимость текста 'Некорректный пароль'")
    public boolean checkIsIncorrectPasswordTextVisible() {
        return INCORRECT_PASSWORD_TEXT.isDisplayed();
    }

    @Step("Кликнуть на ссылку 'Войти'")
    public void clickSignInLink() {
        SIGN_IN_LINK.click();
    }
}
