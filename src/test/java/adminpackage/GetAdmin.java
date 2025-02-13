package adminpackage;

import adminpackage.adminentity.AdminFromList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Random;

import static adminpackage.Auth.*;

import static helper.GetPost.getMethod;

public class GetAdmin {
    public static ArrayList<AdminFromList> ADMINS = new ArrayList<>();

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
            adminFromList.setStatus(adminJson.getString("status"));
            adminFromList.setFirstName(adminJson.getString("firstName"));
            adminFromList.setSecondName(adminJson.getString("secondName"));

            adminFromList.setSkype(adminJson.isNull("skype") ? null : adminJson.getString("skype"));
            adminFromList.setTelegram(adminJson.isNull("telegram") ? null : adminJson.getString("telegram"));
            adminFromList.setPhone(adminJson.isNull("phone") ? null : adminJson.getString("phone"));
            adminFromList.setLastLoginIp(adminJson.isNull("lastLoginIp") ? null : adminJson.getString("lastLoginIp"));
            adminFromList.setLastLoginDt(adminJson.isNull("lastLoginDt") ? null : adminJson.getString("lastLoginDt"));
            adminFromList.setWorkingHours(adminJson.isNull("workingHours") ? null : adminJson.getString("workingHours"));
            adminFromList.setUpdatedAt(adminJson.isNull("updatedAt") ? null : adminJson.getString("updatedAt"));
            adminFromList.setCreatedAt(adminJson.isNull("createdAt") ? null : adminJson.getString("createdAt"));

            ADMINS.add(adminFromList);
        }
    }

    public static void getAdmin(int id) {
        String path = DEV_API_NODE + "/admin/" + id;
        System.out.println(path);
        String responseString = getMethod(path);
        ;
        //  System.out.println(responseString);
    }

    public static Integer getRandomEnableAdmin(ArrayList<AdminFromList> ADMINS) {
        ArrayList<Integer> adminsIds = new ArrayList<>();

        for (int i = 0; i < ADMINS.size(); i++) {

            String status = ADMINS.get(i).getStatus();
            // TODO: equals() = соответсвие
            if (status.equals("enabled")) {
                adminsIds.add(ADMINS.get(i).getId());
            }
        }
        // TODO: взять рандомный
        Integer randomId = adminsIds.get(new Random().nextInt(adminsIds.size()));
        return randomId;
    }


    // TODO: ВОТ ТУТ СДЕЛАТЬ МЕТОД КОТОРЫЙ ВОЗВРАЩАЕТ РАНДОМНОЕ NAME АДМИНА
    


}
