package Domain;

public class User {

    private String username;
    private String firstName;

    // constructors / standard setters / getters
    public User(String UserName, String fName) {
        this.username = UserName;
        firstName = fName;
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
