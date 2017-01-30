import Util.DatabaseUtil;
import Util.DateUtil;
import org.junit.Test;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;


public class DateUtilTest {

    private Format sqlFormat = new SimpleDateFormat("yyyy-mm-dd");
    private Format interialFormat = new SimpleDateFormat("YYYY-MM-DD");

    @Test
    public void toDbFormat() throws Exception {
        assertEquals(sqlFormat.format(new Date()), DateUtil.toDbFormat(new Date()));
    }

    @Test
    public void toStandardFormat() throws Exception {
        assertEquals(interialFormat.format(new Date()), DateUtil.toStandardFormat(new Date()));
    }

}