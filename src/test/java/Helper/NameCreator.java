package Helper;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static AdminPackage.Auth.KEY;

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
