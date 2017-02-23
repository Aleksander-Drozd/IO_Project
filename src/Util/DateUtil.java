package Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static final String STANDARD_FORMAT = "yyyy-MM-dd";

    public static String toStandardString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat(STANDARD_FORMAT);
        return formatter.format(date);
    }
}
