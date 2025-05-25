package site.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import junitparams.JUnitParamsRunner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import site.stellarburgers.model.LoginPage;
import site.stellarburgers.model.MainPage;
import site.stellarburgers.model.RegistrationPage;

import static com.codeborne.selenide.Selenide.clearBrowserLocalStorage;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static site.stellarburgers.Browser.browserChoice;
import static site.stellarburgers.Browser.closeNotChromeBrowser;
import static site.stellarburgers.generator.UserGenerator.DEFAULT_NAME;
import static site.stellarburgers.generator.UserGenerator.DEFAULT_PASSWORD;
import static site.stellarburgers.generator.UserGenerator.SHORT_PASSWORD;
import static site.stellarburgers.generator.UserGenerator.generateRandomEmail;

@RunWith(JUnitParamsRunner.class)
@DisplayName("Регистрация")
public class RegistrationTest {

    MainPage mainPage;
    LoginPage loginPage;
    RegistrationPage registrationPage;
    String newEmail;

    @BeforeClass
    public static void beforeAll() {
        browserChoice();
    }

    @AfterClass
    public static void afterAll() {
        closeNotChromeBrowser();
    }

    @Before
    public void setUp() {
        mainPage = open(MainPage.MAIN_PAGE_URL, MainPage.class);
        mainPage.clickSignInButton();

        loginPage = page(LoginPage.class);
        loginPage.clickRegisterLink();

        registrationPage = page(RegistrationPage.class);
        newEmail = generateRandomEmail();
    }

    @After
    public void tearDown() {
        clearBrowserLocalStorage();
    }

    @Test
    @DisplayName("Регистрация с валидными данными")
    public void registerUserSuccessfully() {
        registrationPage.register(DEFAULT_NAME, newEmail, DEFAULT_PASSWORD);
        loginPage.login(newEmail, DEFAULT_PASSWORD);
        Assert.assertTrue(mainPage.checkIsCheckOutButtonEnabled());
    }

    @Test
    @DisplayName("Регистрация со слишком коротким паролем")
    public void registerUserWithShortPassword() {
        registrationPage.register(DEFAULT_NAME, newEmail, SHORT_PASSWORD);
        Assert.assertTrue(registrationPage.checkIsIncorrectPasswordTextVisible());
    }
}
