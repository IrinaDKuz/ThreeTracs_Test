package exportentitypackage;

import advertpackage.advertentity.AdvertBasicInfoEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.*;

import static adminpackage.Auth.AFFISE_ADVERT_NODE;
import static adminpackage.Auth.authApi;
import static advertpackage.PostAdvert.basicInfoAddEdit;
import static helper.GetPost.getAffiseMethod;

public class ExportEntityTest {

    @Test
    public static void test() throws InterruptedException {
          // String affiseId_1 = "6772cb2dfd41fdce20091c9e";
       String affiseId_1 = "67ac6e95bc7dd6ba3272d28c";
      //  String affiseId_1 = "67ac6e95bc7dd6ba3272d28c";

        Integer threeTracksId_1 =  1370; //1148; //1371
      //  Integer threeTracksId_2 = 1369;

        System.err.println("Считываем данные из Affise у Адверта с id = " + affiseId_1);
        AffiseAdvertInfoEntity affiseAdvertInfoEntityBefore_1 = getAffiseAdvertInfo(affiseId_1);
       // AffiseAdvertInfoEntity affiseAdvertInfoEntityBefore_2 = getAffiseAdvertInfo(affiseId_2);

        System.err.println("Заходим в 3Tracks и полностью меняем Basic поля у Адверта с id = " + threeTracksId_1);
        authApi(104);
        AdvertBasicInfoEntity advertBasicInfoEdit = new AdvertBasicInfoEntity();
        advertBasicInfoEdit.generateMaxFields();
        advertBasicInfoEdit.setId(threeTracksId_1);
        basicInfoAddEdit(true, advertBasicInfoEdit); // редактируем адверта

        Thread.sleep(10000);

        System.err.println("Второй раз считываем данные из Affise у Адверта с id = " + affiseId_1);
        AffiseAdvertInfoEntity affiseAdvertInfoEntityAfter_1 = getAffiseAdvertInfo(affiseId_1);
        // AffiseAdvertInfoEntity affiseAdvertInfoEntityAfter_2 = getAffiseAdvertInfo(affiseId_2);

        System.err.println("Сравниваем до и после из Affise у Адверта с id = " + affiseId_1);
        assertAdvertInfo(affiseAdvertInfoEntityBefore_1, affiseAdvertInfoEntityAfter_1);

       // System.err.println("Сравниваем до и после из Affise у Адверта с id = " + affiseId_2);
        // assertAdvertInfo(affiseAdvertInfoEntityBefore_2, affiseAdvertInfoEntityAfter_2);

    }



    public static AffiseAdvertInfoEntity getAffiseAdvertInfo(String affiseId) {
        String path = AFFISE_ADVERT_NODE + affiseId;
        System.out.println(path);
        String responseString = getAffiseMethod(path);

        JSONObject jsonObject = new JSONObject(responseString);
        System.out.println(jsonObject.toString()
                .replace("],", "],\n")
                .replace("},", "},\n"));

        JSONObject data = jsonObject.getJSONObject("advertiser");

        AffiseAdvertInfoEntity affiseAdvertInfoEntity = new AffiseAdvertInfoEntity();
        affiseAdvertInfoEntity.setId(data.getString("id"));
        affiseAdvertInfoEntity.setTitle(data.getString("title"));
        affiseAdvertInfoEntity.setSiteUrl(data.isNull("url") ? null : data.getString("url"));
        affiseAdvertInfoEntity.setManager(data.isNull("manager") ? null : data.getString("manager"));
        affiseAdvertInfoEntity.setNote(data.isNull("note") ? null : data.getString("note"));
        affiseAdvertInfoEntity.setContact(data.isNull("contact") ? null : data.getString("contact"));
        affiseAdvertInfoEntity.setEmail(data.isNull("email") ? null : data.getString("email"));
        affiseAdvertInfoEntity.setSkype(data.isNull("skype") ? null : data.getString("skype"));
        affiseAdvertInfoEntity.setAddress_1(data.isNull("address_1") ? null : data.getString("address_1"));
        affiseAdvertInfoEntity.setAddress_2(data.isNull("address_2") ? null : data.getString("address_2"));
        affiseAdvertInfoEntity.setCity(data.isNull("city") ? null : data.getString("city"));
        affiseAdvertInfoEntity.setCountry(data.isNull("country") ? null : data.getString("country"));
        affiseAdvertInfoEntity.setZip_code(data.isNull("zip_code") ? null : data.getString("zip_code"));
        affiseAdvertInfoEntity.setVat_code(data.isNull("vat_code") ? null : data.getString("vat_code"));


        if (data.get("tags") instanceof JSONArray) {
            JSONArray tagArray = data.getJSONArray("tags");
            Set<String> tagIdList = new HashSet<>();
            for (int i = 0; i < tagArray.length(); i++) {
                String value = tagArray.getString(i);
                tagIdList.add(value);
            }
            affiseAdvertInfoEntity.setTagsId(tagIdList);
        } else affiseAdvertInfoEntity.setTagsId(null); // Если не массив, устанавливаем null


        if (data.get("manager_obj") instanceof JSONObject) {
            JSONObject managerObj = data.getJSONObject("manager_obj");
            affiseAdvertInfoEntity.setManagerId(managerObj.isNull("id") ? null : managerObj.getString("id"));
        }
        else affiseAdvertInfoEntity.setManagerId(null);
        return affiseAdvertInfoEntity;
    }

    public static void assertAdvertInfo(AffiseAdvertInfoEntity afAdvBefore, AffiseAdvertInfoEntity afAdvAfter ) {
        if (!Objects.equals(afAdvBefore.id, afAdvAfter.id)) {
            System.out.println("Difference in id: " + afAdvBefore.id + " vs " + afAdvAfter.id);
        }
        if (!Objects.equals(afAdvBefore.title, afAdvAfter.title)) {
            System.out.println("Difference in title: " + afAdvBefore.title + " vs " + afAdvAfter.title);
        }
        if (!Objects.equals(afAdvBefore.siteUrl, afAdvAfter.siteUrl)) {
            System.out.println("Difference in siteUrl: " + afAdvBefore.siteUrl + " vs " + afAdvAfter.siteUrl);
        }
        if (!Objects.equals(afAdvBefore.manager, afAdvAfter.manager)) {
            System.out.println("Difference in manager: " + afAdvBefore.manager + " vs " + afAdvAfter.manager);
        }
        if (!Objects.equals(afAdvBefore.managerId, afAdvAfter.managerId)) {
            System.out.println("Difference in managerId: " + afAdvBefore.managerId + " vs " + afAdvAfter.managerId);
        }
        if (!Objects.equals(afAdvBefore.tagsId, afAdvAfter.tagsId)) {
            System.out.println("Difference in tagsId: " + afAdvBefore.tagsId + " vs " + afAdvAfter.tagsId);
        }

        if (!Objects.equals(afAdvBefore.contact, afAdvAfter.contact)) {
            System.out.println("Difference in contact: " + afAdvBefore.contact + " vs " + afAdvAfter.contact);
        }

        if (!Objects.equals(afAdvBefore.email, afAdvAfter.email)) {
            System.out.println("Difference in email: " + afAdvBefore.email + " vs " + afAdvAfter.email);
        }

        if (!Objects.equals(afAdvBefore.skype, afAdvAfter.skype)) {
            System.out.println("Difference in skype: " + afAdvBefore.skype + " vs " + afAdvAfter.skype);
        }

        if (!Objects.equals(afAdvBefore.address_1, afAdvAfter.address_1)) {
            System.out.println("Difference in address_1: " + afAdvBefore.address_1 + " vs " + afAdvAfter.address_1);
        }

        if (!Objects.equals(afAdvBefore.address_2, afAdvAfter.address_2)) {
            System.out.println("Difference in address_2: " + afAdvBefore.address_2 + " vs " + afAdvAfter.address_2);
        }

        if (!Objects.equals(afAdvBefore.city, afAdvAfter.city)) {
            System.out.println("Difference in city: " + afAdvBefore.city + " vs " + afAdvAfter.city);
        }

        if (!Objects.equals(afAdvBefore.country, afAdvAfter.country)) {
            System.out.println("Difference in country: " + afAdvBefore.country + " vs " + afAdvAfter.country);
        }

        if (!Objects.equals(afAdvBefore.zip_code, afAdvAfter.zip_code)) {
            System.out.println("Difference in zip_code: " + afAdvBefore.zip_code + " vs " + afAdvAfter.zip_code);
        }

        if (!Objects.equals(afAdvBefore.vat_code, afAdvAfter.vat_code)) {
            System.out.println("Difference in vat_code: " + afAdvBefore.vat_code + " vs " + afAdvAfter.vat_code);
        }
    }
}
