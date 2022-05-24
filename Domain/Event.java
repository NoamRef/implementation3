package Domain;

public class Event {
    private String description;
    private String time;

    public Event(String desc, String time) {
        description = desc;
        this.time = time;
    }

    public String returnDesc() {
        return time + " " + description;
    }
}
