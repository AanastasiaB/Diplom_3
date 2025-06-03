package site.stellarburgers.generator;

import net.datafaker.Faker;
import site.stellarburgers.model.User;

public class UserGenerator {
    private static final Faker faker = new Faker();
    public static final String SHORT_PASSWORD = "123";

    public static String generateRandomName() {
        return faker.name().firstName();
    }

    public static String generateRandomEmail() {
        return faker.internet().emailAddress();
    }

    public static String generateRandomPassword() {
        return faker.internet().password(6, 10);
    }

    public static User generateRandomUser() {
        return new User(
                generateRandomEmail(),
                generateRandomPassword(),
                generateRandomName()
        );
    }
}
