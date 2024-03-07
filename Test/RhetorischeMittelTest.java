import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RhetorischeMittelTest {

    @BeforeEach
    public void setup(){
    }

    @Test
    public void testIstSpiegelWort() {
        RhetorischeMittel rhetorischeMittel = new RhetorischeMittel();
        assertThrows(NullPointerException.class, () -> rhetorischeMittel.istSpiegelwort(null));

    }

    @Test
    public void testJavaKlausur() {
        RhetorischeMittel rhetorischeMittel = new RhetorischeMittel();
        assertFalse(rhetorischeMittel.istSpiegelwort("Javaklausur"));
    }

    @Test
    public void testEinSpiegelWortIst() {
        RhetorischeMittel rhetorischeMittel = new RhetorischeMittel();
        assertTrue(rhetorischeMittel.istSpiegelwort(""));
    }
    @Test
    public void test2SchuettelWoerterSind() {
        RhetorischeMittel rhetorischeMittel = new RhetorischeMittel();
        assertTrue(rhetorischeMittel.sindSchuettelwoerter("Regallager", "Lagerregal"));

    }

}


