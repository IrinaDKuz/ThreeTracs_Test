package settingspackage;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

import static adminpackage.Auth.DEV_API_NODE;
import static helper.GetPost.getMethod;

public class GetTag {
    public static ArrayList<Integer> TAG_IDS = new ArrayList<>();


    public static void getTagIds() {
        String path = DEV_API_NODE + "/setting/advert-tag/";
        String responseString = getMethod(path);
        JSONObject jsonObject = new JSONObject(responseString);
        JSONObject data = jsonObject.getJSONObject("data");
        JSONArray advertTag = data.getJSONArray("advertTag");


        for (int i = 0; i < data.length(); i++) {
            JSONObject tagJson = advertTag.getJSONObject(i);
            Integer id = tagJson.getInt("id");
            TAG_IDS.add(id);
        }
    }

    public static Integer getRandomTag() {
        if (TAG_IDS.isEmpty())
            getTagIds();
        return TAG_IDS.get(new Random().nextInt(TAG_IDS.size()));
    }
}
