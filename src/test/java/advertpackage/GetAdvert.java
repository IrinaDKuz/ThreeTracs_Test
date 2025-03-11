package advertpackage;

import static adminpackage.Auth.DEV_API_NODE;
import static adminpackage.Auth.authApi;
import static helper.GetPost.getMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.StreamSupport;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import advertpackage.advertentity.AdvertBasicInfoEntity;
import advertpackage.advertentity.AdvertContactEntity;
import advertpackage.advertentity.AdvertContactEntity.Messenger;
import advertpackage.advertentity.AdvertPaymentInfoEntity;
import advertpackage.advertentity.AdvertRequisitesEntity;

public class GetAdvert {
    public static ArrayList<AdvertBasicInfoEntity> ADVERTS = new ArrayList<>();
    public static ArrayList<Integer> ADVERTS_IDS = new ArrayList<>();

    @Test
    public static void test() {
        authApi(104); //авторизуемся 104 админом
        int id = getRandomAdvert(); //выбираем id рандомного адверта
        System.out.println(id); // выводим id выбранного адверта в терминал
        if (!getAdvertContact(id).isEmpty()) // если контакты адверта заполнены
            System.out.println(getAdvertContact(id).getFirst().getEmail()); // то выводим почту в терминал
        System.out.println(getAdvertPaymentInfo(id).getMinPayout()); // выводим Min Payout адверта
    }

    public static AdvertBasicInfoEntity getAdvertBasicInfo(int id) {
        String path = DEV_API_NODE + "/advert/" + id; // генерим URL для запроса
        String responseString = getMethod(path); // передаем GET запрос

        JSONObject jsonObject = new JSONObject(responseString); // поулчаем ответ в виде JSON
        JSONObject data = jsonObject.getJSONObject("data"); // поулчаем значение по ключу data

        AdvertBasicInfoEntity advertBasicInfo = new AdvertBasicInfoEntity(); // создаем место хранения информации по адвертам

        advertBasicInfo.setId(data.getInt("id")); // извлекаем значение ключа id
        advertBasicInfo.setStatus(data.getString("status")); // извлекаем значение ключа status

        advertBasicInfo.setName(data.isNull("name") ? null : data.getString("name")); // проверка на null, если null то подставляем свои значения
        advertBasicInfo.setCompanyLegalName(data.isNull("companyLegalname") ? null : data.getString("companyLegalname")); // проверка на null, если null то подставляем свои значения 
        // advertBasicInfo.setRegistrationNumber(data.getString("registrationNumber"));
        advertBasicInfo.setSiteUrl(data.isNull("siteUrl") ? null : data.getString("siteUrl")); // проверка на null, если null то подставляем свои значения
        advertBasicInfo.setManagerId(data.isNull("managerId") ? null : data.getInt("managerId")); // проверка на null, если null то подставляем свои значения
        advertBasicInfo.setSalesManager(data.isNull("salesManager") ? null : data.getInt("salesManager")); // проверка на null, если null то подставляем свои значения
        advertBasicInfo.setAccountManager(data.isNull("accountManager") ? null : data.getInt("accountManager")); // проверка на null, если null то подставляем свои значения
        advertBasicInfo.setUserRequestSource(data.isNull("userRequestSource") ? null : data.getInt("userRequestSource")); // проверка на null, если null то подставляем свои значения
        advertBasicInfo.setNote(data.isNull("note") ? null : data.getString("note")); // проверка на null, если null то подставляем свои значения

        JSONObject offer = data.getJSONObject("offer"); // извлекаем data из JSON с ключом offer
        // advertBasicInfo.setActiveOffersCount(parseUnknownValueToInteger(offer, "active"));
        // advertBasicInfo.setInactiveOffersCount(parseUnknownValueToInteger(offer, "inactive"));
        // advertBasicInfo.setTotalOffersCount(offer.getInt("total"));
        // advertBasicInfo.setDraftOffersCount(offer.getInt("draft"));

        if (data.get("pricingModel") instanceof JSONArray) { // проверяем есть ли JSON массив
            JSONArray pricingModelArray = data.getJSONArray("pricingModel"); // извлекаем data из JSON с ключом pricingModel
            List<String> listArray = StreamSupport.stream(pricingModelArray.spliterator(), false) //массив из json загнать в наш привычный java массив
                    .map(Object::toString)
                    .toList();
            advertBasicInfo.setPricingModel(listArray);
        } else advertBasicInfo.setPricingModel(null); // Если pricingModel не массив, устанавливаем null

        if (data.get("geo") instanceof JSONArray) { // проверяем есть ли JSON массив
            JSONArray geoArray = data.getJSONArray("geo");
            List<String> listArray = StreamSupport.stream(geoArray.spliterator(), false)
                    .map(Object::toString)
                    .toList();
            advertBasicInfo.setGeo(listArray);
        } else advertBasicInfo.setGeo(null);  // Если не массив, устанавливаем null

        if (data.get("categories") instanceof JSONArray) { // проверяем есть ли JSON массив
            JSONArray categoriesArray = data.getJSONArray("categories");
            Set<Integer> categoriesIdList = new HashSet<>();
            for (int i = 0; i < categoriesArray.length(); i++) {
                int value = categoriesArray.getInt(i);
                categoriesIdList.add(value);
            }
            advertBasicInfo.setCategories(categoriesIdList);
        } else advertBasicInfo.setCategories(null); // Если не массив, устанавливаем null

        if (data.get("tag") instanceof JSONArray) {
            JSONArray tagArray = data.getJSONArray("tag");
            Set<Integer> tagIdList = new HashSet<>();
            for (int i = 0; i < tagArray.length(); i++) {
                int value = tagArray.getInt(i);
                tagIdList.add(value);
            }
            advertBasicInfo.setTagsId(tagIdList);
        } else advertBasicInfo.setTagsId(null); // Если не массив, устанавливаем null
        return advertBasicInfo; // возвращает заполненные данные на адверта
    }

    public static ArrayList<AdvertContactEntity> getAdvertContact(int id) { // получаем список контактов по id
        ArrayList<AdvertContactEntity> contactsList = new ArrayList<>();

        String path = DEV_API_NODE + "/advert/" + id + "/contact"; // URL запроса
        String responseString = getMethod(path); // отправляем азпрос по сформированному URL

        JSONObject jsonObject = new JSONObject(responseString); // преобразуем в ответ в JSON
        JSONArray dataArray = jsonObject.getJSONArray("data"); //извлекаем значение ключа data

        for (int i = 0; i < dataArray.length(); i++) { // перебираем весь ответ
            AdvertContactEntity advertContact = new AdvertContactEntity(); // создаем новое место хранения для контактов адверта
            JSONObject dataObject = dataArray.getJSONObject(i); // получаем данные на конкретный id контакта
            advertContact.setContactID(dataObject.getInt("id")); // получаем id контакта
            advertContact.setPerson(dataObject.getString("person")); // получаем person контакта
            advertContact.setStatus(dataObject.getString("status")); // получаем status контакта
            advertContact.setEmail(dataObject.isNull("email") ? null : dataObject.getString("email")); // если эл почта null то вставляем email
            advertContact.setPosition(dataObject.isNull("position") ? null : dataObject.getString("position")); // если эл position = null то вставляем position

            JSONArray messengersArray = dataObject.getJSONArray("messengers"); // получаем массив по ключу messengers
            ArrayList<Messenger> messengers = new ArrayList<>(); // создаем место хранения messengers
            for (int j = 0; j < messengersArray.length(); j++) { // перебираем все значения в ответе
                JSONObject messengerObject = messengersArray.getJSONObject(j); // поулчаем данные о мессенджете с конкретным id
                Messenger messenger = new Messenger(); // создаем новый объект Messenger
                messenger.setMessengerId(messengerObject.getInt("id")); // устанавливаем id мессенджера
                messenger.setMessengerTypeId(messengerObject.getInt("messengerId")); // устанавливаем id типа мессенджера
                messenger.setMessengerValue(messengerObject.getString("value")); // устанавливаем value 
                messengers.add(messenger);
            }
            advertContact.setMessengers(messengers); // сохраняем список мессенджеров в AdvertContactEntity
            contactsList.add(advertContact); // сохраняем в список контактов
        }
        return contactsList;
    }

    public static AdvertPaymentInfoEntity getAdvertPaymentInfo(int id) {
        AdvertPaymentInfoEntity advertPaymentInfoEntity = new AdvertPaymentInfoEntity();

        String path = DEV_API_NODE + "/advert/" + id + "/payment-info";
        String responseString = getMethod(path);

        JSONObject jsonObject = new JSONObject(responseString);
        JSONObject dataObject = jsonObject.getJSONObject("data");

        advertPaymentInfoEntity.setMinPayout(dataObject.isNull("minPayout") ? 0 : dataObject.getFloat("minPayout"));
        JSONArray dataArray = dataObject.getJSONArray("payments");
        for (int i = 0; i < dataArray.length(); i++) {
            AdvertRequisitesEntity advertRequisites = new AdvertRequisitesEntity();
            JSONObject requisitesObject = dataArray.getJSONObject(i);

            advertRequisites.setRequisitesId(requisitesObject.getInt("id"));
            advertRequisites.setCurrency(requisitesObject.getString("currency"));
            advertRequisites.setPaymentSystemId(requisitesObject.getInt("payment"));

            JSONObject requisites = requisitesObject.getJSONObject("requisites");
            Map<String, String> requisitesMap = new HashMap<>();

            for (String key : requisites.keySet()) {
                requisitesMap.put(key, requisites.optString(key, ""));
            }
            advertRequisites.setRequisites(requisitesMap);
            advertPaymentInfoEntity.addAdvertRequisitesList(advertRequisites);
        }
        return advertPaymentInfoEntity;
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
