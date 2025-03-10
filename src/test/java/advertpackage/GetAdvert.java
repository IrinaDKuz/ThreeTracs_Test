package advertpackage;

import adminpackage.adminentity.AdminEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Random;

import static adminpackage.Auth.DEV_API_NODE;
import static helper.GetPost.getMethod;

public class GetAdvert {
    public static ArrayList<AdvertEntity> ADVERTS = new ArrayList<>();
    public static ArrayList<Integer> ADVERTS_IDS = new ArrayList<>();


    @Test
    public static void getAdverts() {
        String path = DEV_API_NODE + "/advertisers/";
        String responseString = getMethod(path);

        JSONObject jsonObject = new JSONObject(responseString);
        JSONObject data = jsonObject.getJSONObject("data");
        JSONArray advertsArray = data.getJSONArray("adverts");

        for (int i = 0; i < advertsArray.length(); i++) {
            AdminEntity admin = new AdminEntity();
            JSONObject adminJson = advertsArray.getJSONObject(i);

            // из json заполняем наш лист с админами данными
            admin.setId(adminJson.getInt("id"));
            admin.setEmail(adminJson.getString("email"));
            admin.setStatus(adminJson.getString("status"));
            admin.setFirstName(adminJson.getString("firstName"));
            admin.setSecondName(adminJson.getString("secondName"));

            // указываем, что если в json ответе null, то в наш фал пишем null
            admin.setSkype(adminJson.isNull("skype") ? null : adminJson.getString("skype"));
            admin.setTelegram(adminJson.isNull("telegram") ? null : adminJson.getString("telegram"));
            admin.setPhone(adminJson.isNull("phone") ? null : adminJson.getString("phone"));
            admin.setLastLoginIp(adminJson.isNull("lastLoginIp") ? null : adminJson.getString("lastLoginIp"));
            admin.setLastLoginDt(adminJson.isNull("lastLoginDt") ? null : adminJson.getString("lastLoginDt"));
            admin.setWorkingHours(adminJson.isNull("workingHours") ? null : adminJson.getString("workingHours"));
            admin.setUpdatedAt(adminJson.isNull("updatedAt") ? null : adminJson.getString("updatedAt"));
            admin.setCreatedAt(adminJson.isNull("createdAt") ? null : adminJson.getString("createdAt"));

            // ВОТ ТУТ ВОПРОС... ПОГУГЛИЛ ЧТО МЫ ТАК ДОБАВЛЯЕМ АДМИНА В СПИСОК, НО НЕ ПОНЯЛ
            ADMINS.add(admin); // adminFromList - каждый новый админ в цикле. записываем в ADMINS
        }
    }

    public static void getAdvertsIds() {
        String path = DEV_API_NODE + "/advertisers/";
        String responseString = getMethod(path);

        JSONObject jsonObject = new JSONObject(responseString);
        JSONObject data = jsonObject.getJSONObject("data");
        JSONArray advertsArray = data.getJSONArray("adverts");

        for (int i = 0; i < advertsArray.length(); i++) {
            JSONObject advertJson = advertsArray.getJSONObject(i);
            ADVERTS_IDS.add(advertJson.getInt("id"));
        }
    }

    public static void getAdvert(int id) {
        String path = DEV_API_NODE + "/admin/" + id; // урл запроса инфы на админа с конкретным id
        System.out.println(path); // выводим урл в терминал
        String responseString = getMethod(path); // отправляем гет запрос

    }

    public static Integer getRandomAdvert() {
        return ADVERTS_IDS.get(new Random().nextInt(ADVERTS_IDS.size()));
    }
    

}
