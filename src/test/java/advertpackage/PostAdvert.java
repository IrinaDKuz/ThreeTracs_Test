package advertpackage;

import advertpackage.advertentity.AdvertBasicInfoEntity;
import advertpackage.advertentity.AdvertContactEntity;
import advertpackage.advertentity.AdvertContactEntity.Messenger;
import advertpackage.advertentity.AdvertPaymentInfoEntity;
import advertpackage.advertentity.AdvertRequisitesEntity;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.StreamSupport;

import static adminpackage.Auth.DEV_API_NODE;
import static adminpackage.Auth.authApi;
import static adminpackage.PostDeleteAdmin.getEntityIdFromResponse;
import static helper.ConstantsName.*;
import static helper.GetPost.getMethod;
import static helper.GetPost.postMethod;

public class PostAdvert {

    @Test
    public static void testAdvert() {

        authApi(104); // авторизуемся 104-м админом

        AdvertBasicInfoEntity advertBasicInfo = new AdvertBasicInfoEntity(); // создаем новый объект и заполняем его полями
        advertBasicInfo.generateMaxFields();
        basicInfoAddEdit(false, advertBasicInfo); //создаем нового адверта
        System.out.println(advertBasicInfo.getId()); // выводит id созданного адверта в консоль

        // advertId = advertPrimaryInfoAdd.getAdvertId();
        // primaryInfoAssert(advertPrimaryInfoAdd, primaryInfoGet(false));

        // Allure.step("Редактируем Primary Info Адверта id=" + advertId);
        AdvertBasicInfoEntity advertBasicInfoEdit = new AdvertBasicInfoEntity();
        advertBasicInfoEdit.generateMaxFields();
        advertBasicInfoEdit.setId(advertBasicInfo.getId()); // находим id адверта

        basicInfoAddEdit(true, advertBasicInfoEdit); // редактируем адверта

        // primaryInfoAssert(advertPrimaryInfoEdit, primaryInfoGet(false));

        // Allure.step("Удаляем Адверта (чтобы не засорять базу) id=" + advertId);
        // deleteMethod("advert", String.valueOf(advertId));
        // assertDelete(String.valueOf(advertId), "advert");*/
    }


    public static void basicInfoAddEdit(Boolean isEdit, AdvertBasicInfoEntity advertBasicInfo) { //????
        Gson gson = new Gson(); //?
        JsonObject jsonObject = gson.fromJson(createJsonAdvert(advertBasicInfo), JsonObject.class);
        System.out.println(jsonObject.toString().replace("],", "],\n")); //  выводим в терминал объект который создали и делаем это красивым

        String path = isEdit ? DEV_API_NODE + "/advert/" + advertBasicInfo.getId() + "/edit" :
                DEV_API_NODE + "/advert/new"; // формируем УРЛ для запроса

        String responseBody = postMethod(path, jsonObject);

        if (!isEdit) {
            System.out.println(ADD_RESPONSE + responseBody);
            JSONObject jsonResponse = new JSONObject(responseBody); // записываем id созданного адверта
            advertBasicInfo.setId(jsonResponse.getJSONObject("data").getInt("advertId"));
        } else {
            System.out.println(EDIT_RESPONSE + responseBody); //подтверждение успешного редактирования
        }
    }

    private static JsonObject createJsonAdvert(AdvertBasicInfoEntity advertBasicInfo) {
        JsonObject jsonObject = new JsonObject(); // создаем новый Json объект
        jsonObject.addProperty("tab", "primary"); // добавляем tab и primary

        JsonObject advertObject = new JsonObject(); // создаем новый Json объект // нужно пояснение
        advertObject.addProperty("status", advertBasicInfo.getStatus().toLowerCase());
        advertObject.addProperty("name", advertBasicInfo.getName());
        advertObject.addProperty("managerId", advertBasicInfo.getManagerId());
        advertObject.addProperty("accountManager", advertBasicInfo.getAccountManager());
        advertObject.addProperty("salesManager", advertBasicInfo.getSalesManager());
        advertObject.addProperty("siteUrl", advertBasicInfo.getSiteUrl());
        advertObject.addProperty("companyLegalname", advertBasicInfo.getCompanyLegalName());
        advertObject.addProperty("note", advertBasicInfo.getNote());
        advertObject.addProperty("userRequestSource",advertBasicInfo.getUserRequestSource());

        List<String> pricingModelList = advertBasicInfo.getPricingModel(); //
        JsonArray pricingModelArray = new JsonArray(); // формируем массив
        pricingModelList.forEach(pricingModelArray::add);
        advertObject.add("pricingModel", pricingModelArray);

        Set<Integer> tagList = advertBasicInfo.getTagsId();
        JsonArray tagArray = new JsonArray();
        tagList.forEach(tagArray::add);
        advertObject.add("tag", tagArray);

        Set<Integer> categoriesList = advertBasicInfo.getCategories();
        JsonArray categoriesArray = new JsonArray();
        categoriesList.forEach(categoriesArray::add);
        advertObject.add("categories", categoriesArray);

        List<String> geoList = advertBasicInfo.getGeo();
        JsonArray geoArray = new JsonArray();
        geoList.forEach(geoArray::add);
        advertObject.add("geo", geoArray);

        jsonObject.add("advert", advertObject);
        return jsonObject;
    }
}
