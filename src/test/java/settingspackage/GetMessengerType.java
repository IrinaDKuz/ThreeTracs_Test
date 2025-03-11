package settingspackage;

import advertpackage.advertentity.AdvertBasicInfoEntity;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

import static adminpackage.Auth.DEV_API_NODE;
import static helper.GetPost.getMethod;

public class GetMessengerType {
    public static ArrayList<Integer> MESSENGERS_IDS = new ArrayList<>();


    public static void getMessengerTypeIds() {
        String path = DEV_API_NODE + "/setting/messenger/";
        String responseString = getMethod(path);

        JSONObject jsonObject = new JSONObject(responseString);
        JSONArray data = jsonObject.getJSONArray("data");

        for (int i = 0; i < data.length(); i++) {

            JSONObject messengerJson = data.getJSONObject(i);
            JSONObject generalJson = messengerJson.getJSONObject("general");
            Integer id = generalJson.getInt("id");
            MESSENGERS_IDS.add(id);
        }
    }


    public static Integer getRandomMessengerType() {
        if (MESSENGERS_IDS.isEmpty())
            getMessengerTypeIds();
        return MESSENGERS_IDS.get(new Random().nextInt(MESSENGERS_IDS.size()));
    }
}
