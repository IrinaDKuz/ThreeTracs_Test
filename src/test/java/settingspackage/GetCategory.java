package settingspackage;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

import static adminpackage.Auth.DEV_API_NODE;
import static helper.GetPost.getMethod;

public class GetCategory {
    public static ArrayList<Integer> CATEGORY_IDS = new ArrayList<>();


    public static void getCategoryIds() {
        String path = DEV_API_NODE + "/setting/category/";
        String responseString = getMethod(path);

        JSONObject jsonObject = new JSONObject(responseString);
        JSONArray data = jsonObject.getJSONArray("data");

        for (int i = 0; i < data.length(); i++) {
            JSONObject categoryJson = data.getJSONObject(i);
            JSONObject generalJson = categoryJson.getJSONObject("general");
            Integer id = generalJson.getInt("id");
            CATEGORY_IDS.add(id);
        }
    }

    public static Integer getRandomCategory() {
        if (CATEGORY_IDS.isEmpty())
            getCategoryIds();
        return CATEGORY_IDS.get(new Random().nextInt(CATEGORY_IDS.size()));
    }
}
