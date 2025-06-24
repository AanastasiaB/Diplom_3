package site.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import junitparams.JUnitParamsRunner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import site.stellarburgers.api.UserClient;
import site.stellarburgers.model.LoginPage;
import site.stellarburgers.model.MainPage;
import site.stellarburgers.model.RegistrationPage;
import site.stellarburgers.model.User;

import static com.codeborne.selenide.Selenide.clearBrowserLocalStorage;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static site.stellarburgers.Browser.browserChoice;
import static site.stellarburgers.Browser.closeNotChromeBrowser;
import static site.stellarburgers.generator.UserGenerator.*;

@RunWith(JUnitParamsRunner.class)
@DisplayName("Регистрация")
public class RegistrationTest {

    MainPage mainPage;
    LoginPage loginPage;
    RegistrationPage registrationPage;
    UserClient userClient;
    User user;

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
        userClient = new UserClient();
        mainPage = open(MainPage.MAIN_PAGE_URL, MainPage.class);
        mainPage.clickSignInButton();

        loginPage = page(LoginPage.class);
        loginPage.clickRegisterLink();

        registrationPage = page(RegistrationPage.class);
        user = new User(generateRandomEmail(), generateRandomPassword(), generateRandomName());
    }

    @After
    public void tearDown() {
        try {
            if (user != null) {
                Response loginResponse = userClient.login(user);
                if (loginResponse.statusCode() == 200) {
                    String accessToken = loginResponse.then().extract().path("accessToken");
                    if (accessToken != null) {
                        userClient.delete(accessToken);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Не удалось удалить пользователя: " + e.getMessage());
        }
        clearBrowserLocalStorage();
    }

    @Test
    @DisplayName("Регистрация с валидными данными")
    @Description("Проверка успешной регистрации с валидными данными")
    public void registerUserSuccessfully() {
        registrationPage.register(user.getName(), user.getEmail(), user.getPassword());
        loginPage.login(user.getEmail(), user.getPassword());
        Assert.assertTrue(mainPage.checkIsCheckOutButtonEnabled());
    }

    @Test
    @DisplayName("Регистрация со слишком коротким паролем")
    @Description("Проверка ошибки при регистрации с паролем короче 6 символов")
    public void registerUserWithShortPassword() {
        registrationPage.register(user.getName(), user.getEmail(), SHORT_PASSWORD);
        Assert.assertTrue(registrationPage.checkIsIncorrectPasswordTextVisible());
    }
}
