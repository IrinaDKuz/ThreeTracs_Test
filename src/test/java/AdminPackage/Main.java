package AdminPackage;

import static AdminPackage.Auth.authApi;
import static AdminPackage.GetAdmin.getAdmin;

public class Main {
    public static void main(String[] args) {
        authApi(104);
        getAdmin();
    }
}
