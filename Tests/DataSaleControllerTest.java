import Controller.DataSaleController;
import POJO.Sale;
import mockit.Mock;
import mockit.Mocked;
import mockit.Verifications;
import org.junit.Test;

import java.net.URL;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

public class DataSaleControllerTest {

    DataSaleController dataSaleController;

    @Mocked
    Sale sale;

    @Test
    public void getSale() throws Exception {
        dataSaleController = new DataSaleController();

        assertNull("Should return Null",dataSaleController.getSale());
    }

}