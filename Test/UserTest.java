import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserTest {

    private static User user;

    @BeforeAll
    public static void create() {
        user = new User();
    }


    @Test
    public void testDefaultUsername() {
        assertEquals("Default", user.getUsername());
    }


    @Test
    public void testSetUsername() {
        user.setUsername("Jack");
        assertEquals("Jack", user.getUsername());
    }
    // Methode, die testet, ob man null fuer username setzen kann mit der set-Methode. Denn am Anfang wurde Attribut user den Wert "Default" gegeben.

    @Test
    public void testSetUsernameForNull() {
        user.setUsername(null);
        assertNull(null, user.getUsername());

    }


}