package advertpackage;

import advertpackage.advertentity.AdvertBasicInfoEntity;
import advertpackage.advertentity.AdvertContactEntity;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

import static adminpackage.Auth.DEV_API_NODE;
import static adminpackage.Auth.authApi;
import static advertpackage.GetAdvert.getRandomAdvert;
import static helper.ConstantsName.ADD_RESPONSE;
import static helper.ConstantsName.EDIT_RESPONSE;
import static helper.GetPost.postMethod;

public class PostAdvertContact {

    @Test
    public static void test() {
        authApi(104);
        Integer advertId = getRandomAdvert();

        AdvertContactEntity advertContact = new AdvertContactEntity();
        advertContact.generateContactFields();
        contactAddEdit(false, advertContact, advertId);
        System.out.println(advertContact.getContactID());

        // advertId = advertPrimaryInfoAdd.getAdvertId();
        // primaryInfoAssert(advertPrimaryInfoAdd, primaryInfoGet(false));

        // Allure.step("Редактируем Primary Info Адверта id=" + advertId);
        AdvertContactEntity advertContactEdit = new AdvertContactEntity();
        advertContactEdit.generateContactFields();
        advertContactEdit.setContactID(advertContact.getContactID());

        contactAddEdit(true, advertContact, advertId);

        // primaryInfoAssert(advertPrimaryInfoEdit, primaryInfoGet(false));
        // Allure.step("Удаляем Адверта (чтобы не засорять базу) id=" + advertId);
        // deleteMethod("advert", String.valueOf(advertId));
        // assertDelete(String.valueOf(advertId), "advert");*/
    }


    public static void contactAddEdit(Boolean isEdit, AdvertContactEntity advertContact, Integer advertId) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(createJsonAdvertContact(advertContact), JsonObject.class);
        System.out.println(jsonObject.toString().replace("],", "],\n"));

        String path = isEdit ? DEV_API_NODE + "/advert/" + advertId + "/contact/" + advertContact.getContactID() + "/edit"
                : DEV_API_NODE + "/advert/" + advertId + "/contact/add";

        String responseBody = postMethod(path, jsonObject);


        if (!isEdit) {
            System.out.println(ADD_RESPONSE + responseBody);
            JSONObject jsonResponse = new JSONObject(responseBody);
            advertContact.setContactID(jsonResponse.getJSONObject("data").getInt("advertContact"));
        } else {
            System.out.println(EDIT_RESPONSE + responseBody);
        }
    }

    private static JsonObject createJsonAdvertContact(AdvertContactEntity advertContact) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("status", advertContact.getStatus().toLowerCase());
        jsonObject.addProperty("person", advertContact.getPerson());
        jsonObject.addProperty("email", advertContact.getEmail());
        jsonObject.addProperty("position", advertContact.getPosition());

        JsonArray messengersArray = new JsonArray();

        for (AdvertContactEntity.Messenger messenger : advertContact.getMessengers()) {
            JsonObject jsonMessenger = new JsonObject();
            if (messenger.getMessengerId() != null) {
                jsonMessenger.addProperty("id", messenger.getMessengerId());
            }
            jsonMessenger.addProperty("messengerId", messenger.getMessengerTypeId());
            jsonMessenger.addProperty("value", messenger.getMessengerValue());
            messengersArray.add(jsonMessenger);
        }
        jsonObject.add("messengers", messengersArray);
        return jsonObject;
    }
}
