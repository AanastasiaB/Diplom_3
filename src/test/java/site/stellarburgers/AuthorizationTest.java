package site.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.stellarburgers.api.UserClient;
import site.stellarburgers.generator.UserGenerator;
import site.stellarburgers.model.LoginPage;
import site.stellarburgers.model.MainPage;
import site.stellarburgers.model.PasswordRecoveryPage;
import site.stellarburgers.model.PersonalAccountPage;
import site.stellarburgers.model.RegistrationPage;
import site.stellarburgers.model.User;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static org.junit.Assert.assertTrue;

@DisplayName("Авторизация")
public class AuthorizationTest {

    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private PasswordRecoveryPage passwordRecoveryPage;
    private PersonalAccountPage personalAccountPage;
    private UserClient userClient;
    private User user;
    private String accessToken;

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = UserGenerator.generateRandomUser();
        userClient.create(user);

        accessToken = userClient.login(user)
                .then()
                .extract()
                .path("accessToken");

        mainPage = open(MainPage.MAIN_PAGE_URL, MainPage.class);
        loginPage = page(LoginPage.class);
        registrationPage = page(RegistrationPage.class);
        passwordRecoveryPage = page(PasswordRecoveryPage.class);
        personalAccountPage = page(PersonalAccountPage.class);
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            userClient.delete(accessToken);
        }
        closeWebDriver();
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    @Description("Проверка входа в систему через кнопку 'Войти в аккаунт' на главной странице")
    public void signInBySignInButtonOnMainPage() {
        mainPage.clickSignInButton();
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(mainPage.checkIsCheckOutButtonEnabled());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("Проверка входа в систему через кнопку 'Личный кабинет' в хедере")
    public void signInByPersonalAccountLink() {
        mainPage.clickPersonalAccountLink();
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(mainPage.checkIsCheckOutButtonEnabled());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверка входа в систему через кнопку в форме регистрации")
    public void signInBySignInButtonOnRegistrationPage() {
        mainPage.clickSignInButton();
        loginPage.clickRegisterLink();
        registrationPage.clickSignInLink();
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(mainPage.checkIsCheckOutButtonEnabled());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверка входа в систему через кнопку в форме восстановления пароля")
    public void signInBySignInButtonOnPasswordRecoveryPage() {
        mainPage.clickSignInButton();
        loginPage.clickPasswordRecoveryLink();
        passwordRecoveryPage.clickSignInLink();
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(mainPage.checkIsCheckOutButtonEnabled());
    }

    @Test
    @DisplayName("Выход из аккаунта")
    @Description("Проверка выхода из системы через личный кабинет")
    public void signOut() {
        mainPage.clickSignInButton();
        loginPage.login(user.getEmail(), user.getPassword());
        mainPage.clickPersonalAccountLink();
        personalAccountPage.clickSignOutButton();
        loginPage.clickLogoLink();
        assertTrue(mainPage.checkIsSignInButtonEnabled());
    }
}
