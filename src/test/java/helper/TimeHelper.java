package helper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeHelper {

    public static String getCurrentDateAndTimeToNumber() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMddHHmmssSSS");
        return dateFormat.format(currentDate);
    }
}
