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
}
