import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class LogitTest {

    private Logit logit;

    @BeforeEach
    public void create() {
        logit = new Logit();
    }

    @Test
    public void berechneNatuerlicheLogarithmusVon0KommaEins() {
        double ergebnis = logit.calc(0.1);
        assertEquals(-2.197224577336219, ergebnis);
    }

    @Test
    public void berechneNatuerlicheLogarithmusVon0KommaDrei() {
        double ergebnis = logit.calc(0.3);
        assertEquals(-0.8472978603872036, ergebnis);
    }

    @Test
    public void berechneNatuerlicheLogarithmusVon0KommaFuenf() {
        double ergebnis = logit.calc(0.5);
        assertEquals(0, ergebnis);
    }

    @Test
    public void berechneNatuerlicheLogarithmusVon0KommaSieben() {
        double ergebnis = logit.calc(0.7);
        assertEquals(0.8472978603872036, ergebnis, 1e-15);
    }

    @Test
    public void berechneNatuerlicheLogarithmusVon0KommaNeun() {
        double ergebnis = logit.calc(0.9);
        assertEquals(2.197224577336219, ergebnis, 1e-15);
    }
    // Exception Testen

    /**
     * Diese Methode,testet ob ein IllegalARGUMENT EXCEPTION GEWORFEN WIRD.
     */
    @Test
    public void berechneNatuerlicheLogarithmusVon0() {
        assertThrows(IllegalArgumentException.class, () -> logit.calc(0));
    }

    @Test
    public void berechneNatuerlicheLogarithmusVon0ExceptionMessage() {
        Executable executable = () -> logit.calc(0);
        String erwarteteNachricht = "Waehlen Sie einen Wert zwischen 0 und 1";
        Exception exception = assertThrows(IllegalArgumentException.class, executable);
        assertEquals(erwarteteNachricht, exception.getMessage());
    }
}

