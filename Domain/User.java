package Domain;

public class User {

    private String name;
    private String email;

    // constructors / standard setters / getters
    public User (String iName, String iEmail) {
        name=iName;
        email=iEmail;
    }
    public void setName (String iName){
        name=iName;
    }
    public void setEmail (String iEmail){
        email=iEmail;
    }
    public String getName () {return name;}
    public String getEmail () {return email;}
}
