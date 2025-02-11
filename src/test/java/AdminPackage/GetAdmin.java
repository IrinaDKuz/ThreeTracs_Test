package AdminPackage;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static AdminPackage.Auth.*;

import static io.restassured.RestAssured.given;

public class GetAdmin {

    public static void getAdmin() {

        String path = DEV_API_NODE + "/admin/";
        System.out.println(path);

        Response response = RestAssured.given()
                .contentType(ContentType.URLENC)
                .header("Authorization", KEY)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .get(path);

        String responseString = response.getBody().asString();
        System.out.println(responseString);

    }
}