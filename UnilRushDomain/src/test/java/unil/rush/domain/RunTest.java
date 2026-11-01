package unil.rush.domain;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class RunTest {
    @Test
    void negativeScore()
    {
        Player delta = new Player("john", Faculty.HEC, "asasasaa");
        delta.setPlayerID(UUID.randomUUID());
        Run r = new Run(delta.getPlayerID());
        assertThrows(IllegalArgumentException.class,()->r.setScore(-1));
    }
    @Test
    void dayZero()
    {
        Player delta = new Player("john", Faculty.HEC, "asasasaa");
        delta.setPlayerID(UUID.randomUUID());
        Run r = new Run(delta.getPlayerID());
        assertThrows(IllegalArgumentException.class,()->r.setDay(0));
    }
    @Test
    void dayNegative()
    {
        Player delta = new Player("john", Faculty.HEC, "asasasaa");
        delta.setPlayerID(UUID.randomUUID());
        Run r = new Run(delta.getPlayerID());
        assertThrows(IllegalArgumentException.class,()->r.setDay(-1));
    }
    @Test
    void setANullPlayer()
    {
        Player delta = new Player("john", Faculty.HEC, "asasasaa");
        delta.setPlayerID(UUID.randomUUID());
        Run r = new Run();
        assertThrows(IllegalArgumentException.class,()->r.setPlayerID(null));
    }
    @Test
    void addWarningToAFinishStat()
    {
        Run r = new Run(UUID.randomUUID());
        r.addWarning();
        r.addWarning();
        r.addWarning();
        assertThrows(IllegalStateException.class,()->r.addWarning());
    }
    @Test
    void   threeWarningsLoseTheRun()
    {
        Run r = new Run(UUID.randomUUID());
        r.addWarning();
        r.addWarning();
        r.addWarning();
        assertEquals(r.getStatus(),RunStatus.LOST);
        assertEquals(r.getDefeatCause(),DefeatCause.EXPELLED);
    }

    @Test
    void newRunStartsCorrectly()
    {
        Run l = new Run(UUID.randomUUID());
        assertEquals(l.getDay(), 1);
        assertEquals(l.getScore(),0);
        assertEquals(l.getDefeatCause(),null);
        assertEquals(l.getStatus(),RunStatus.IN_PROGRESS);
    }


}
