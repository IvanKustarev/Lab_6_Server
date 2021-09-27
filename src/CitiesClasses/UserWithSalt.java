package CitiesClasses;

public class UserWithSalt extends User{

    private String salt;

    public UserWithSalt(String name, String password, String salt) {
        super(name, password);
        this.salt = salt;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
