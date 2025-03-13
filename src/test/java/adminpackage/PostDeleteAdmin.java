package adminpackage;

import adminpackage.adminentity.AdminEntity;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static adminpackage.Auth.DEV_API_NODE;
import static adminpackage.Auth.authApi;
import static helper.ConstantsName.*;
import static helper.GetPost.deleteMethod;
import static helper.GetPost.postMethod;

public class PostDeleteAdmin { //создаем новый класс

    @Test // начало теста
    public static void createNewAdmin() { //создаем нового админа
        authApi(104); //авторизовываемся в система (под 104-м админом)
        AdminEntity newAdmin = new AdminEntity(); //создаем нового админа
        newAdmin.generateFields(); //заполняем новому админу поля из файла AdminEntity
        postNewAdmin(newAdmin); //отправляем заполненные данные на нового админа

        // расписать до сюда
        newAdmin.generateFields();
        postEditAdmin(newAdmin);

        deleteAdmin(newAdmin.getId());
    }

    public static void postNewAdmin(AdminEntity newAdmin) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(createJsonAdmin(newAdmin, false), JsonObject.class);

        String responseBody = postMethod(DEV_API_NODE + "/admin/new", jsonObject);
        System.out.println(ADD_RESPONSE + responseBody); // выводим ответ

        newAdmin.setId(getEntityIdFromResponse(responseBody));
        System.out.println(newAdmin.getId());
        // Allure.step("Ответ на add: " + responseBody);
    }
// разобрать до сюда

    public static void postEditAdmin(AdminEntity editAdmin) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(createJsonAdmin(editAdmin, true), JsonObject.class);

        String responseBody = postMethod(DEV_API_NODE + "/admin/" + editAdmin.getId() + "/edit", jsonObject);
        System.out.println(EDIT_RESPONSE + responseBody);
        Assert.assertEquals(responseBody, SUCCESS_TRUE );
    }


    public static int getEntityIdFromResponse(String responseBody) {
        JSONObject jsonObject = new JSONObject(responseBody); // парсим поулченный json
        JSONObject data = jsonObject.getJSONObject("data"); // берем data
        Integer id = data.getInt("id");
        return id;
    }

    private static JsonObject createJsonAdmin(AdminEntity newAdmin, Boolean isEdit) {
        JsonObject jsonObject = new JsonObject();
        JsonObject adminObject = new JsonObject();

        adminObject.addProperty("status", newAdmin.getStatus());
        adminObject.addProperty("email", newAdmin.getEmail());
        adminObject.addProperty("firstName", newAdmin.getFirstName());
        adminObject.addProperty("secondName", newAdmin.getSecondName());
        adminObject.addProperty("phone", newAdmin.getPhone());
        adminObject.addProperty("skype", newAdmin.getSkype());
        adminObject.addProperty("telegram", newAdmin.getTelegram());
        adminObject.addProperty("workingHours", newAdmin.getWorkingHours());

        if (!isEdit)
            adminObject.addProperty("plainPassword", newAdmin.getPassword());

        // jsonObject.addProperty("roleId", 3); TODO сделать роль

        // Allure.step(DATA + jsonObject.toString().replace("],", "],\n"));
        // attachJson(String.valueOf(jsonObject), DATA);
        if (isEdit)
            jsonObject.add("admin_edit", adminObject);
        else
            jsonObject.add("admin", adminObject);

        System.out.println(jsonObject.toString().replace("],", "],\n"));
        return jsonObject;
    }

    public static void deleteAdmin(Integer id) {
        String responseBody = deleteMethod(DEV_API_NODE + "/admin/" + id);
        System.out.println(DELETE_RESPONSE + responseBody);
        Assert.assertEquals(responseBody, SUCCESS_TRUE );
    }
}
