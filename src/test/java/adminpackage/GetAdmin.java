package adminpackage;

import adminpackage.adminentity.AdminFromList;
import org.json.JSONArray;
import org.json.JSONObject;

import static adminpackage.Auth.*;

import static helper.GetPost.getMethod;

public class GetAdmin {

    public static void getAdmins() {
        String path = DEV_API_NODE + "/admin/";
        String responseString = getMethod(path);

        System.out.println("Ответ на get: " + responseString);
        // attachJson(responseBody, GET_RESPONSE);

        JSONObject jsonObject = new JSONObject(responseString);
        JSONObject data = jsonObject.getJSONObject("data");
        JSONArray adminArray = data.getJSONArray("admin");

        for (int i = 0; i < adminArray.length(); i++) {
            AdminFromList adminFromList = new AdminFromList();
            JSONObject dataObject = adminArray.getJSONObject(i);
        }
    }

    public static void getAdmin(int id) {
        String path = DEV_API_NODE + "/admin/" + id;
        System.out.println(path);
        String responseString = getMethod(path);;
        System.out.println(responseString);
    }


}