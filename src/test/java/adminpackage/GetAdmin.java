package adminpackage;

import adminpackage.adminentity.AdminEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Random;

import static adminpackage.Auth.*;

import static helper.GetPost.getMethod;

public class GetAdmin {
    public static ArrayList<AdminEntity> ADMINS = new ArrayList<>(); // ArrayList здесь хранятся все админы

    @Test
    public static void getAdmins() { 
        String path = DEV_API_NODE + "/admin/"; // урл для отправки запроса
        String responseString = getMethod(path); // отправляем гет запрос

        //  System.out.println("Ответ на get: " + responseString);
        // attachJson(responseBody, GET_RESPONSE);

        JSONObject jsonObject = new JSONObject(responseString); // парсим поулченный json
        JSONObject data = jsonObject.getJSONObject("data"); // берем data
        JSONArray adminArray = data.getJSONArray("admin"); // берем массив админов

// проходимся по всем админам
        for (int i = 0; i < adminArray.length(); i++) {
            AdminEntity admin = new AdminEntity();
            JSONObject adminJson = adminArray.getJSONObject(i);

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

    
    public static void getAdmin(int id) {
        String path = DEV_API_NODE + "/admin/" + id; // урл запроса инфы на админа с конкретным id
        System.out.println(path); // выводим урл в терминал
        String responseString = getMethod(path); // отправляем гет запрос
        ;
        //  System.out.println(responseString);
    }

    public static Integer getRandomEnableAdmin(ArrayList<AdminEntity> ADMINS) {
        ArrayList<Integer> adminsIds = new ArrayList<>(); // создали список где будут храниться id админов

        // проходимся по всем админам в списке
        for (int i = 0; i < ADMINS.size(); i++) {

            String status = ADMINS.get(i).getStatus();
            // TODO: equals() = соответсвие
            if (status.equals("enabled")) {
                adminsIds.add(ADMINS.get(i).getId());
            }
        }
        // TODO: взять рандомный id админа
        Integer randomId = adminsIds.get(new Random().nextInt(adminsIds.size())); // создаем переменную randomId
        return randomId;
    }


    // TODO: ВОТ ТУТ СДЕЛАТЬ МЕТОД КОТОРЫЙ ВОЗВРАЩАЕТ РАНДОМНОЕ NAME АДМИНА
    // возвращаем строку (имя) случайного админа со статусом enable
    public static String getRandomEnableAdminFirstName(ArrayList<AdminEntity> ADMINS) {
        ArrayList<String> adminsFirstNames = new ArrayList<>(); // создаем список куда быдем записывать firstName админов

        //проходим по всем админам
        for (int i = 0; i < ADMINS.size(); i++) { // не меняется (статичная)
            String status = ADMINS.get(i).getStatus(); // может изменяться, но не меняется (смотрим статус)
            if (status.equals("enabled")) { // выбираем со статусом "enabled"
            adminsFirstNames.add(ADMINS.get(i).getFirstName()); // записали FirstName админа в список, если у него статус "enabled"
        }
    }
    String firstName = adminsFirstNames.get(new Random().nextInt(adminsFirstNames.size()));
    return firstName;
    }

}
