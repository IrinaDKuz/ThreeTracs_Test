package settingspackage;

import org.json.JSONArray;
import org.json.JSONObject;
import settingspackage.settingsentity.PaymentInfoEntity;

import java.util.*;

import static adminpackage.Auth.DEV_API_NODE;
import static helper.GetPost.getMethod;

public class GetPaymentType {
    public static ArrayList<Integer> PAYMENT_TYPE_IDS = new ArrayList<>();


    public static void getPaymentTypeIds() {
        String path = DEV_API_NODE + "/setting/payment_system/";
        String responseString = getMethod(path);

        JSONObject jsonObject = new JSONObject(responseString);
        JSONArray data = jsonObject.getJSONArray("data");

        for (int i = 0; i < data.length(); i++) {
            JSONObject messengerJson = data.getJSONObject(i);
            Integer id = messengerJson.getInt("id");
            PAYMENT_TYPE_IDS.add(id);
        }
    }

    public static Integer getRandomPaymentType() {
        if (PAYMENT_TYPE_IDS.isEmpty())
            getPaymentTypeIds();
        return PAYMENT_TYPE_IDS.get(new Random().nextInt(PAYMENT_TYPE_IDS.size()));
    }

    public static PaymentInfoEntity getRandomPaymentSystemInfo(int paymentSystemId) {
        PaymentInfoEntity paymentInfoEntity = new PaymentInfoEntity();

        String path = DEV_API_NODE + "/setting/payment_system/";
        String responseString = getMethod(path);

        JSONObject jsonObject = new JSONObject(responseString);
        JSONArray data = jsonObject.getJSONArray("data");

        for (int i = 0; i < data.length(); i++) {

            JSONObject messengerJson = data.getJSONObject(i);
            int id = messengerJson.getInt("id");
            if (id == paymentSystemId) {
                JSONArray currencyArray = messengerJson.getJSONArray("currency");
                List<String> currencyList = new ArrayList<>();
                for (int j = 0; j < currencyArray.length(); j++) {
                    currencyList.add(currencyArray.getString(j));
                }
                paymentInfoEntity.setCurrency(currencyList);

                JSONArray fieldsArray = messengerJson.getJSONArray("fields");
                Map<String, Boolean> fieldsMap = new HashMap<>();
                // Преобразование JSONArray в Map
                for (int j = 0; j < fieldsArray.length(); j++) {
                    JSONObject fieldObject = fieldsArray.getJSONObject(i);
                    String title = fieldObject.getString("title");
                    Boolean required = fieldObject.getBoolean("required");
                    fieldsMap.put(title, required);
                }
                paymentInfoEntity.setFields(fieldsMap);
            }
        }
        return paymentInfoEntity;

    }


}
