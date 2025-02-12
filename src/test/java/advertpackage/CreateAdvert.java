/*
package AdvertPackage;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import static io.restassured.RestAssured.given;


public class CreateAdvert {

    public static void postAdvert() {

        // JsonObject jsonObject = new JsonObject();
        // jsonObject.addProperty("tab", "primary");

        JsonObject advertObject = new JsonObject();
        advertObject.addProperty("name", "Advert name");
       // advertObject.addProperty("managerId", getRandomAdmin());

        // advertObject.addProperty("status", advertPrimaryInfo.getStatus().toLowerCase());

        // advertObject.addProperty("managerId", Integer.parseInt(advertPrimaryInfo.getManagerId()));
        // advertObject.addProperty("accountManager", Integer.parseInt(advertPrimaryInfo.getAccountManagerId()));
        // advertObject.addProperty("salesManager", Integer.parseInt(advertPrimaryInfo.getSalesManagerId()));
        // advertObject.addProperty("siteUrl", advertPrimaryInfo.getSiteUrl());
        // advertObject.addProperty("companyLegalname", advertPrimaryInfo.getCompanyLegalName());
        // advertObject.addProperty("note", advertPrimaryInfo.getNote());
        // advertObject.addProperty("userRequestSource", Integer.parseInt(advertPrimaryInfo.getUserRequestSource()));


        String requestBody = "{ \"username\": \"" + email + "\"," +
                " \"password\": \"" + password + "\" }";

        Response response = given()
                .body(requestBody)
                .post("/auth/login") // Адрес запроса
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        String key = response.path("data.key");
        KEY = "Bearer " + key;
        System.out.println(KEY);
    }


    public static int getRandomUserId() {
        Set<Integer> keys = USERS.keySet();
        Integer[] keyArray = keys.toArray(new Integer[0]);
        Random random = new Random();
        int randomIndex = random.nextInt(keyArray.length);
        return keyArray[randomIndex];
    }
}

*/
