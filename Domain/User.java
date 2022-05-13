package Domain;

public class User {

    private String firstName;
    private String username;

    // constructors / standard setters / getters
    public User(String fName, String UserName) {
        firstName = fName;
        this.username = UserName;
    }

    // getter & setters
    public void setName(String fName) {
        firstName = fName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getUserName() {
        return this.username;
    }

    // functions

    public void Login() {

    }

    public void LogOut() {

    }
}
