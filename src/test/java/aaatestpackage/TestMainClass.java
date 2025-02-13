package aaatestpackage;

import org.testng.annotations.Test;

import static adminpackage.Auth.authApi;
import static adminpackage.GetAdmin.*;

public class TestMainClass {

    @Test
    public static void test() {
        authApi(104);
        getAdmins();
        getAdmin(103);
        System.out.println(ADMINS.get(3).getFirstName());
        System.out.println(getRandomEnableAdmin(ADMINS));
    }
}
