package helper;

import com.github.javafaker.Faker;

public class NameCreator {

    public static String createFullName() {
        Faker faker = new Faker();
        return faker.name().fullName();
    }

    public static String createFirstName() {
        Faker faker = new Faker();
        return faker.name().firstName();

    }
}
