package helper;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static adminpackage.Auth.KEY;

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

    public static String postMethod(String path, JsonObject jsonObject) {
        System.out.println(path);
        Response response = RestAssured.given()
                .contentType(ContentType.URLENC)
                .header("Authorization", KEY)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(jsonObject.toString())
                .post(path);

        System.out.println(response);

        return response.getBody().asString();
    }

    public static String deleteMethod(String path) {
        System.out.println(path);
        Response response = RestAssured.given()
                .contentType(ContentType.URLENC)
                .header("Authorization", KEY)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .delete(path);

        return response.getBody().asString();
    }

    public static String getAffiseMethod(String path) {

        Response response = RestAssured.given()
                .contentType(ContentType.URLENC)
                .header("api-key", "f9764bf2be8cb5dfc79631cea1863a8a")
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .get(path);

        return response.getBody().asString();
    }

}
