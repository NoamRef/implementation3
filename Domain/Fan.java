package Domain;

public class Fan {

    private String firstName;

    // constructors / standard setters / getters
    public Fan(String fName) {
        firstName = fName;
    }

    public void setName(String fName) {
        firstName = fName;
    }

    public String getName () {return firstName;}
