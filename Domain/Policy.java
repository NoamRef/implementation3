package Domain;

import java.util.List;

public interface Policy {
    public List<Game> Apply(List<Team> teams);

    public String getName();
}
