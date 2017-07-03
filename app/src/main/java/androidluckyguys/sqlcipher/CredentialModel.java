package androidluckyguys.sqlcipher;

/**
 * Created by lucky on 03/07/2017.
 */

public class CredentialModel {

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    String adminId,password;
}
