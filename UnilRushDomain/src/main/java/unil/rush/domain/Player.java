package unil.rush.domain;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import org.mindrot.jbcrypt.BCrypt;

public class Player {
    private UUID playerID;
    private String username;
    private Faculty faculty;
    private String passwordHash;
    private String avatar= "default";

    public Player()
    {

    }
    public Player(String username, Faculty faculty, String password)
    {
        setUsername(username);
        this.faculty = faculty;
        setPassword(password);
    }
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String username)
    {
        if (username == null || username.length() <=2 || username.length() >= 21)
        {
            throw new IllegalArgumentException("Username must be between 3 and 20 characters");
        }
        this.username = username;
        return;
    }
    public Faculty getFaculty()
    {
        return(faculty);
    }
    public void setFaculty(Faculty faculty)
    {
        this.faculty=faculty;
    }
    public String getAvatar()
    {
        return(avatar);
    }
    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }
    public UUID getPlayerID()
    {
        return playerID;
    }
    public  void setPlayerID(UUID playerID)
    {
        this.playerID=playerID;
    }
    public void setPassword(String password)
    {
        if (password == null)
            throw new IllegalArgumentException("le mot de passe est requis");

        if (password.length() <= 7)
            throw new IllegalArgumentException("le mot de passe doit faire au moins 8 charactères");
        if (password.getBytes(StandardCharsets.UTF_8).length >72 )
            throw new IllegalArgumentException("le mot de passe a dépasser le nombre d'octet protégé");
        this.passwordHash = BCrypt.hashpw(password,BCrypt.gensalt());
    }
    public boolean checkPassword(String password)
    {
        if (this.passwordHash == null || password == null || password.getBytes(StandardCharsets.UTF_8).length >72 )
            return(false);
        return (BCrypt.checkpw(password, this.passwordHash));
    }
    // probablement à supprimer plus tard juste utile pour les tests et voir comment marcher Bcyrpt
    public String getPasswordHash()
    {
        return (this.passwordHash);
    }

    @Override
    public String toString()
    {
        return username + " (" + faculty + ")";
    }
}
