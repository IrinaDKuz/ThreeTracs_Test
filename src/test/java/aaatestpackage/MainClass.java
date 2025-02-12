package aaatestpackage;


import static adminpackage.Auth.authApi;
import static adminpackage.GetAdmin.getAdmin;
import static adminpackage.GetAdmin.getAdmins;

public class MainClass {
    public static void main(String[] args) {
        authApi(104);
        getAdmins();
        getAdmin(103);
    }
}
