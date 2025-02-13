package adminpackage;

import adminpackage.adminentity.AdminFromList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static adminpackage.Auth.*;

import static helper.GetPost.getMethod;

public class GetAdmin {

    @Test
    public static void getAdmins() {
        String path = DEV_API_NODE + "/admin/";
        String responseString = getMethod(path);

      //  System.out.println("Ответ на get: " + responseString);
        // attachJson(responseBody, GET_RESPONSE);

        JSONObject jsonObject = new JSONObject(responseString);
        JSONObject data = jsonObject.getJSONObject("data");
        JSONArray adminArray = data.getJSONArray("admin");

        for (int i = 0; i < adminArray.length(); i++) {
            AdminFromList adminFromList = new AdminFromList();
            JSONObject adminJson = adminArray.getJSONObject(i);

            adminFromList.setId(adminJson.getInt("id"));
            adminFromList.setEmail(adminJson.getString("email"));
            // TODO: Заполнить остальное кроме роль
            adminFromList.setStatus(adminJson.getString("status"));
            adminFromList.setFirstName(adminJson.getString("firstName"));
            adminFromList.setSecondName(adminJson.getString("secondName"));
            adminFromList.setSkype(adminJson.getString("skype"));
            adminFromList.setTelegram(adminJson.getString("telegram"));
            adminFromList.setPhone(adminJson.getString("phone"));
            adminFromList.setLastLoginIp(adminJson.getString("lastLoginIp"));
            adminFromList.setLastLoginDt(adminJson.getString("lastLoginDt"));
            adminFromList.setWorkingHours(adminJson.getString("workingHours"));
            adminFromList.setUpdatedAt(adminJson.getString("updatedAt"));
            adminFromList.setCreatedAt(adminJson.getString("createdAt"));




            System.out.println(adminFromList.getId());
            System.out.println(adminFromList.getEmail());
            System.out.println(adminFromList.getStatus());
            System.out.println(adminFromList.getFirstName());
            System.out.println(adminFromList.getSecondName());
            System.out.println(adminFromList.getSkype());
            System.out.println(adminFromList.getTelegram());
            System.out.println(adminFromList.getPhone());
            System.out.println(adminFromList.getLastLoginIp());
            System.out.println(adminFromList.getLastLoginDt());
            System.out.println(adminFromList.getWorkingHours());
            System.out.println(adminFromList.getUpdatedAt());
            System.out.println(adminFromList.getCreatedAt());


        }
    }

    public static void getAdmin(int id) {
        String path = DEV_API_NODE + "/admin/" + id;
        System.out.println(path);
        String responseString = getMethod(path);;
      //  System.out.println(responseString);
    }


}