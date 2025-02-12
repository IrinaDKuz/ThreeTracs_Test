package aaaTestPackage;


import static AdminPackage.Auth.authApi;
import static AdminPackage.GetAdmin.getAdmin;
import static AdminPackage.GetAdmin.getAdmins;

public class Main {
    public static void main(String[] args) {
        authApi(104);
        getAdmins();
        getAdmin(103);
    }
}
