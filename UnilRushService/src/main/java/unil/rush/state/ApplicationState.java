package unil.rush.state;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import unil.rush.domain.Player;
import unil.rush.domain.Run;

import java.util.*;

@ApplicationScoped
public class ApplicationState {
    private Map<UUID, Player> players;
    private Map<UUID, Run> runs;

    public ApplicationState() {

        players = new HashMap<>();
        runs = new HashMap<>();
    }

    public void addPlayer(Player player) {
        UUID id = UUID.randomUUID();
        while (players.containsKey(id))
            id = UUID.randomUUID();

        player.setPlayerID(id);
        players.put(id, player);
    }

    public Player getPlayer(UUID id) {
        return (players.get(id));
    }

    public List<Player> getAllPlayers() {
        return new ArrayList<>(players.values());
    }

    public boolean removePlayer(UUID id) {
        if (!players.containsKey(id))
            return (false);
        players.remove(id);
        return (!(players.containsKey(id)));
    }

    public boolean updatePlayer(UUID id, Player change) {
        Player player = players.get(id);

        if (player == null)
            return false;
        if (change.getAvatar() != null)
            player.setAvatar(change.getAvatar());
        if (change.getUsername() != null)
            player.setUsername(change.getUsername());
        if (change.getFaculty() != null)
            player.setFaculty(change.getFaculty());
        return true;
    }

    public  Run addRun(Run run)
    {
        if(run.getPlayerID() == null || !players.containsKey(run.getPlayerID()))
            return (null);
        UUID id = UUID.randomUUID();
        while(runs.containsKey(id))
            id =UUID.randomUUID();
        run.setRunID(id);
        run.start();
        runs.put(id, run);
        return (run);
    }

    public Run getRun(UUID id)
    {
        return(runs.get(id));
    }
    public List<Run> getAllRuns()
    {
        return new ArrayList<>(runs.values());
    }
    public boolean removeRun(UUID id) {
        if (!runs.containsKey(id))
            return (false);
        runs.remove(id);
        return (!(runs.containsKey(id)));
    }
    public boolean updateRun(UUID id, Run changes)
    {
        Run test = runs.get(id);

        if (test == null)
            return false;
        if (changes.getScore() > 0)
            test.setScore(changes.getScore());
        if (changes.getDay() > 0)
            test.setDay((changes.getDay()));
        return true;
    }
    public boolean addWarning(UUID id)
    {
        Run test = runs.get(id);
        if(test == null)
            return (false);
        test.addWarning();
        return(true);
    }
}
