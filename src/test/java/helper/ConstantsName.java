package helper;

import java.util.HashMap;
import java.util.Map;

public class ConstantsName {

    public static String EDIT_RESPONSE = "Ответ на edit: ";
    public static String ADD_RESPONSE = "Ответ на add: ";
    public static String DELETE_RESPONSE = "Ответ на delete: ";

    public static String SUCCESS_TRUE =  "{\"success\":true}";


    public final static Map<String, String> MODEL_TYPES_MAP = new HashMap<>() {{
        put("cpa", "Cpa");
        put("revshare", "RevShare");
        put("other", "Other");
        put("hybrid", "Hybrid");
    }};

    public final static Map<String, String> ADVERT_STATUS_MAP = new HashMap<>() {
        {
            put("put_on_search", "Put on search");
            put("assigned_to_sales_manager", "Assigned to Sales manager");
            put("in_touch", "In touch");
            put("discussion", "Discussion");
            put("integration", "Integration");
            put("active", "Active");
            put("paused", "Paused");
            put("declined", "Declined");
        }
    };
}
