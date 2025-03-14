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
        String affiseId = "67caf3662d2c7c1cd4aa7520";
        Integer threeTracksId = 1158;

        System.err.println("Считываем данные из Affise у Адверта с id = " + affiseId);
        AffiseAdvertInfoEntity affiseAdvertInfoEntityBefore = getAffiseAdvertInfo(affiseId);

        System.err.println("Заходим в 3Tracks и полностью меняем Basic поля у Адверта с id = " + threeTracksId);
        authApi(104);
        AdvertBasicInfoEntity advertBasicInfoEdit = new AdvertBasicInfoEntity();
        advertBasicInfoEdit.generateMaxFields();
        advertBasicInfoEdit.setId(threeTracksId);
        basicInfoAddEdit(true, advertBasicInfoEdit); // редактируем адверта

        Thread.sleep(10000);

        System.err.println("Второй раз считываем данные из Affise у Адверта с id = " + affiseId);
        AffiseAdvertInfoEntity affiseAdvertInfoEntityAfter = getAffiseAdvertInfo(affiseId);

        System.err.println("Сравниваем до и после из Affise у Адверта с id = " + affiseId);
        assertAdvertInfo(affiseAdvertInfoEntityBefore, affiseAdvertInfoEntityAfter);
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
        affiseAdvertInfoEntity.setSiteUrl(data.isNull("siteUrl") ? null : data.getString("siteUrl"));
        affiseAdvertInfoEntity.setManager(data.isNull("manager") ? null : data.getString("manager"));

        if (data.get("tags") instanceof JSONArray) {
            JSONArray tagArray = data.getJSONArray("tags");
            Set<Integer> tagIdList = new HashSet<>();
            for (int i = 0; i < tagArray.length(); i++) {
                int value = tagArray.getInt(i);
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
    }
}
