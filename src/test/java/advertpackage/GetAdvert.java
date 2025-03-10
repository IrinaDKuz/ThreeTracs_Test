package advertpackage;

import adminpackage.Auth;
import advertpackage.advertentity.AdvertBasicInfoEntity;
import advertpackage.advertentity.AdvertContactEntity;
import advertpackage.advertentity.AdvertContactEntity.Messenger;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.StreamSupport;

import static adminpackage.Auth.DEV_API_NODE;
import static adminpackage.Auth.authApi;
import static helper.GetPost.getMethod;

public class GetAdvert {
    public static ArrayList<AdvertBasicInfoEntity> ADVERTS = new ArrayList<>();
    public static ArrayList<Integer> ADVERTS_IDS = new ArrayList<>();

    @Test
    public static void test() {
        authApi(104);
        System.out.println(getAdvertContact(1362).getFirst().getEmail());
    }

        public static AdvertBasicInfoEntity getAdvertBasicInfo(int id) {
        String path = DEV_API_NODE + "/advert/" + id;
        String responseString = getMethod(path);

        JSONObject jsonObject = new JSONObject(responseString);
        JSONObject data = jsonObject.getJSONObject("data");

        AdvertBasicInfoEntity advertBasicInfo = new AdvertBasicInfoEntity();

        advertBasicInfo.setId(data.getInt("id"));
        advertBasicInfo.setStatus(data.getString("status"));

        advertBasicInfo.setName(data.isNull("name") ? null : data.getString("name"));
        advertBasicInfo.setCompanyLegalName(data.isNull("companyLegalname") ? null : data.getString("companyLegalname"));
        // advertBasicInfo.setRegistrationNumber(data.getString("registrationNumber"));
        advertBasicInfo.setSiteUrl(data.isNull("siteUrl") ? null : data.getString("siteUrl"));
        advertBasicInfo.setManagerId(data.isNull("managerId") ? null : data.getInt("managerId"));
        advertBasicInfo.setSalesManager(data.isNull("salesManager") ? null : data.getInt("salesManager"));
        advertBasicInfo.setAccountManager(data.isNull("accountManager") ? null : data.getInt("accountManager"));
        advertBasicInfo.setUserRequestSource(data.isNull("userRequestSource") ? null : data.getInt("userRequestSource"));
        advertBasicInfo.setNote(data.isNull("note") ? null : data.getString("note"));

        JSONObject offer = data.getJSONObject("offer");
        // advertBasicInfo.setActiveOffersCount(parseUnknownValueToInteger(offer, "active"));
        // advertBasicInfo.setInactiveOffersCount(parseUnknownValueToInteger(offer, "inactive"));
        // advertBasicInfo.setTotalOffersCount(offer.getInt("total"));
        // advertBasicInfo.setDraftOffersCount(offer.getInt("draft"));

        if (data.get("pricingModel") instanceof JSONArray) {
            JSONArray pricingModelArray = data.getJSONArray("pricingModel");
            List<String> listArray = StreamSupport.stream(pricingModelArray.spliterator(), false)
                    .map(Object::toString)
                    .toList();
            advertBasicInfo.setPricingModel(listArray);
        } else advertBasicInfo.setPricingModel(null);

        if (data.get("geo") instanceof JSONArray) {
            JSONArray geoArray = data.getJSONArray("geo");
            List<String> listArray = StreamSupport.stream(geoArray.spliterator(), false)
                    .map(Object::toString)
                    .toList();
            advertBasicInfo.setGeo(listArray);
        } else advertBasicInfo.setGeo(null);

        if (data.get("categories") instanceof JSONArray) {
            JSONArray categoriesArray = data.getJSONArray("categories");
            Set<Integer> categoriesIdList = new HashSet<>();
            for (int i = 0; i < categoriesArray.length(); i++) {
                int value = categoriesArray.getInt(i);
                categoriesIdList.add(value);
            }
            advertBasicInfo.setCategories(categoriesIdList);
        } else advertBasicInfo.setCategories(null);

        if (data.get("tag") instanceof JSONArray) {
            JSONArray tagArray = data.getJSONArray("tag");
            Set<Integer> tagIdList = new HashSet<>();
            for (int i = 0; i < tagArray.length(); i++) {
                int value = tagArray.getInt(i);
                tagIdList.add(value);
            }
            advertBasicInfo.setTagsId(tagIdList);
        } else advertBasicInfo.setTagsId(null);
        return advertBasicInfo;
    }

    public static ArrayList<AdvertContactEntity> getAdvertContact(int id) {

        ArrayList<AdvertContactEntity> contactsList = new ArrayList<>();

        String path = DEV_API_NODE + "/advert/" + id + "/contact";
        String responseString = getMethod(path);

        JSONObject jsonObject = new JSONObject(responseString);
        JSONArray dataArray = jsonObject.getJSONArray("data");

        for (int i = 0; i < dataArray.length(); i++) {
            AdvertContactEntity advertContact = new AdvertContactEntity();
            JSONObject dataObject = dataArray.getJSONObject(i);
            advertContact.setContactID(dataObject.getInt("id"));
            advertContact.setPerson(dataObject.getString("person"));
            advertContact.setStatus(dataObject.getString("status"));
            advertContact.setEmail(dataObject.isNull("email")? null : dataObject.getString("email"));
            advertContact.setPosition(dataObject.isNull("position") ? null : dataObject.getString("position"));

            JSONArray messengersArray = dataObject.getJSONArray("messengers");
            ArrayList<Messenger> messengers = new ArrayList<>();
            for (int j = 0; j < messengersArray.length(); j++) {
                JSONObject messengerObject = messengersArray.getJSONObject(j);
                Messenger messenger = new Messenger();
                messenger.setMessengerId(messengerObject.getInt("id"));
                messenger.setMessengerTypeId(messengerObject.getInt("messengerId"));
                messenger.setMessengerValue(messengerObject.getString("value"));
                messengers.add(messenger);
            }
            advertContact.setMessengers(messengers);
            contactsList.add(advertContact);
        }
        return contactsList;
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

    public static Integer getRandomAdvert() {
        if (ADVERTS_IDS.isEmpty())
            getAdvertsIds();
        return ADVERTS_IDS.get(new Random().nextInt(ADVERTS_IDS.size()));
    }
}
