package Helper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static AdminPackage.Auth.KEY;

public class GetPost {
    public static String getMethod(String path) {

        Response response = RestAssured.given()
                .contentType(ContentType.URLENC)
                .header("Authorization", KEY)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .get(path);

        return response.getBody().asString();
    }
}
