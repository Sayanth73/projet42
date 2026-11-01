package unil.rush.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    @Test
    void validePlayerCreated()
    {
        Player j = new Player("Tom jedusor",Faculty.HEC, "MOTDEPASSE");
        assertEquals("Tom jedusor", j.getUsername());
    }
    @Test
    void tooShortUsernameRejected()
    {
        assertThrows(IllegalArgumentException.class,()->new Player("le",Faculty.HEC,"moemodmedmeodm"));
    }
    @Test
    void nullistheUsernameRejected()
    {
        assertThrows(IllegalArgumentException.class,()-> new Player(null,Faculty.HEC,"asdasasda"));
    }
    @Test
    void tooLongUsernameRejected()
    {
        assertThrows(IllegalArgumentException.class,()-> new Player("asldamsdmlamsdlmalmdsamldmlasmldmladmlsalmd", Faculty.HEC, "qsassasa"));
    }

    @Test
    void noNullPassword()
    {
        assertThrows(IllegalArgumentException.class,()-> new Player("dasad", Faculty.HEC, null));
    }

    @Test
    void tooShortPassword()
    {
        assertThrows(IllegalArgumentException.class,()-> new Player("dasad", Faculty.HEC, "123"));
    }

    @Test
    void tooLongBytesPassword()
    {
        assertThrows(IllegalArgumentException.class,()-> new Player("esa",Faculty.SSP, "é".repeat(40)));
    }

    @Test
    void tryToCheckWithNull()
    {
        Player j = new Player("jon", Faculty.FGSE, "notnull123");
        assertEquals(false,j.checkPassword(null));
    }
    @Test
    void creationOfAPlayerIsGood()
    {
        assertDoesNotThrow(()->new Player("Dumbeldore", Faculty.FTSR, "Elalalala1"));
    }
    @Test
    void tryToCheckAvalidPassword()
    {
        Player d = new Player("John", Faculty.HEC, "Fitzeral");
        assertEquals(true,d.checkPassword("Fitzeral"));
        assertEquals(false,d.checkPassword("notgood"));
    }
    @Test
    void tryTheLimitOfCheck()
    {
        Player d = new Player("John", Faculty.HEC, "a".repeat(72));
        assertEquals(false,d.checkPassword("a".repeat(72)+"XXX"));
    }

}
