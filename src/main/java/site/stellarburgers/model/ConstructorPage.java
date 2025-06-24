package site.stellarburgers.model;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ConstructorPage {

    @FindBy(how = How.XPATH, using = "//div[span[text()='Булки']]")
    private SelenideElement BUN_TAB;

    @FindBy(how = How.XPATH, using = "//div[span[text()='Соусы']]")
    private SelenideElement SAUCE_TAB;

    @FindBy(how = How.XPATH, using = "//div[span[text()='Начинки']]")
    private SelenideElement FILLING_TAB;

    @Step("Кликнуть на таб 'Соусы'")
    public void clickSauceTab() {
        SAUCE_TAB.click();
    }

    @Step("Кликнуть на таб 'Начинки'")
    public void clickFillingTab() {
        FILLING_TAB.click();
    }

    @Step("Проверить, что выбран таб 'Булки'")
    public boolean checkIsBunTabSelected() {
        return BUN_TAB.getAttribute("class").contains("current");
    }

    @Step("Проверить, что выбран таб 'Соусы'")
    public boolean checkIsSauceTabSelected() {
        return SAUCE_TAB.getAttribute("class").contains("current");
    }

    @Step("Проверить, что выбран таб 'Начинки'")
    public boolean checkIsFillingTabSelected() {
        return FILLING_TAB.getAttribute("class").contains("current");
    }
}
