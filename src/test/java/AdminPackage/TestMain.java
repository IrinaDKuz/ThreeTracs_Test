package AdminPackage;

import org.testng.annotations.Test;

import static AdminPackage.Auth.authApi;
import static AdminPackage.GetAdmin.getAdmin;
import static AdminPackage.GetAdmin.getAdmins;

public class TestMain {

    @Test
    public static void test() {
        authApi(104);
        getAdmins();
        getAdmin(103);
    }
}
