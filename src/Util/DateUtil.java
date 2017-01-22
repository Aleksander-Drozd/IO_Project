package Util;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static Format sqlFormat = new SimpleDateFormat("yyyy-mm-dd");
    private static Format interialFormat = new SimpleDateFormat("YYYY-MM-DD");

    public static String  toDbFormat(Date date){
        return sqlFormat.format(date);
    }

    public static String toStandardFormat(Date date){
        return interialFormat.format(date);
    }
}
