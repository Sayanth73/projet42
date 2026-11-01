package unil.rush.state;

import jakarta.enterprise.context.ApplicationScoped;
import unil.rush.domain.Player;

import java.util.*;

@ApplicationScoped
public class ApplicationState {
    private Map<UUID, Player> players;

    public ApplicationState() {
        players = new HashMap<>();
    }

    public void addPlayer(Player player)
    {
        UUID id = UUID.randomUUID();
        while (players.containsKey(id))
            id = UUID.randomUUID();

        player.setPlayerID(id);
        players.put(id,player);
    }
    public Player getPlayer(UUID id)
    {
        return(players.get(id));
    }
    public List<Player> getAllPlayers()
    {
        return new ArrayList<>(players.values());
    }
    public boolean removePlayer(UUID id)
    {
        if (!players.containsKey(id))
            return (false);
        players.remove(id);
        return(!(players.containsKey(id)));
    }

    public boolean updatePlayer(UUID id, Player change)
    {
        Player player = players.get(id);

        if (player == null)
            return false;
        if(change.getAvatar() != null)
            player.setAvatar(change.getAvatar());
        if(change.getUsername() != null)
            player.setUsername(change.getUsername());
        if(change.getFaculty() != null)
            player.setFaculty(change.getFaculty());
        return true ;
    }
}
