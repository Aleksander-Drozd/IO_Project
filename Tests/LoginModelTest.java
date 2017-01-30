import Model.LoginModel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class LoginModelTest {

    LoginModel loginModel;

    @Before
    public void setUp(){
        loginModel = new LoginModel();
    }

    @Test
    public void getEmployee() throws Exception {
        assertNotNull(loginModel.getEmployee("test","test"));
        assertEquals("5",loginModel.getEmployee("test","test").getPosition());

        loginModel = new LoginModel();
        assertNull(loginModel.getEmployee("nielogin","niehaslo"));
    }

}