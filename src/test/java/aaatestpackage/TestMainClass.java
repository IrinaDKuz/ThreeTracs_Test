package aaatestpackage;

import org.testng.annotations.Test;

import static adminpackage.Auth.authApi;
import static adminpackage.GetAdmin.getAdmin;
import static adminpackage.GetAdmin.getAdmins;

public class TestMainClass {

    @Test
    public static void test() {
        authApi(104);
        getAdmins();
        getAdmin(103);
    }
}
