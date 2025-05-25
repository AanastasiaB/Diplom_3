package site.stellarburgers.generator;

public class UserGenerator {

    public static String DEFAULT_NAME = "User";
    public static String WORKING_EMAIL = "user123123@mail.ru";
    public static String DEFAULT_PASSWORD = "abc123";
    public static String SHORT_PASSWORD = "123";
    public static String DEFAULT_EMAIL = "stdr@test.ru";

    public static String generateRandomEmail() {
        return Math.random() + DEFAULT_EMAIL;
    }
}
