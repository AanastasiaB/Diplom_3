package site.stellarburgers.model;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MainPage extends Header {

    public final static String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    @FindBy(how = How.XPATH, using = "//button[text()='Войти в аккаунт']")
    private SelenideElement SIGN_IN_BUTTON;

    @FindBy(how = How.XPATH, using = "//button[text()='Оформить заказ']")
    private SelenideElement CHECKOUT_BUTTON;

    @Step("Нажать кнопку 'Войти в аккаунт'")
    public void clickSignInButton() {
        SIGN_IN_BUTTON.click();
    }

    @Step("Проверить, что кнопка 'Оформить заказ' активна")
    public boolean checkIsCheckOutButtonEnabled() {
        return CHECKOUT_BUTTON.isEnabled();
    }

    @Step("Проверить, что кнопка 'Войти в аккаунт' активна")
    public boolean checkIsSignInButtonEnabled() {
        return SIGN_IN_BUTTON.isEnabled();
    }
}
