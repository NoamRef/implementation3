package Domain;

public class User {

    private String username;
    private String firstName;
    private int id;

    // constructors / standard setters / getters
    public User(String UserName, String fName) {
        this.username = UserName;
        firstName = fName;
    }

    public User(String UserName, String fName, int id) {
        this.username = UserName;
        firstName = fName;
        this.id = id;
    }

    // getter & setters
    public void setName(String uName) {
        username = uName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getUserName() {
        return this.username;
    }

    public int getID() {
        return this.id;
    }
}
