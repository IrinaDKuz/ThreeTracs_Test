package AdminPackage;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import static io.restassured.RestAssured.given;


public class Auth {
    public static String KEY;

    public static final String DEV_NODE = "https://admin.3tracks.link/";
    public static final String DEV_API_NODE = "https://api.admin.3tracks.link";

    static Map<Integer, Map<String, String>> USERS = new HashMap<>() {
        {
            put(1, new HashMap<>() {{
                put("email", "admin@3tracks.online");
                put("password", "password");

            }});

            put(55, new HashMap<>() {{
                put("email", "Lenora45@gmail.com");
                put("password", "password");
            }});

            put(103, new HashMap<>() {{
                put("email", "petrpetrovpp2023@gmail.com");
                put("password", "password");

            }});
            put(104, new HashMap<>() {{
                put("email", "petrpetrovpp2024@gmail.com");
                put("password", "password");
            }});
        }
    };


    @Test
    public static void testAuthApi() {
        authApi(104);
    }


    public static void authApi(Integer user) {
        System.out.println("Пользователь, которым зашли в систему: " + user);

        RestAssured.baseURI = DEV_API_NODE ;
        String email = USERS.get(user).get("email");
        String password = USERS.get(user).get("password");

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

