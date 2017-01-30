import DAO.SaleDAO;
import Model.SaleModel;
import POJO.Sale;
import mockit.Mocked;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JMockit.class)
public class SaleModelTest {

    @Mocked
    SaleDAO saleDAO;

    SaleModel saleModel;

    @Before
    public void setUp() {
        saleModel = new SaleModel();
    }

    @Test
    public void getSalesObservableList() throws Exception {
        assertNotNull(saleModel.getSalesObservableList());
    }

    @Test
    public void addSale() throws Exception {
        saleModel.addSale(new Sale());
        new Verifications(){{ saleDAO.addSale(withInstanceOf(Sale.class)); times = 1; }};
    }

    @Test
    public void updateSale() throws Exception {
        saleModel.addSale(new Sale());
        new Verifications(){{ saleDAO.addSale(withInstanceOf(Sale.class)); times = 1; }};
    }

}