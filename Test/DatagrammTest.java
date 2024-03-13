import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


 class Datagrammtest {

    Datagramm datagramm;

    /** Hilfsmethode setSegmentValue
     * Setzt die flags der n-ten 7-Segmentanzeige so, dass sie die Dezimalziffer value repräsentierten.
     * @param nummer Der Wert für n. Wertebereich [1,4]
     * @param value Die darzustellende Dezimalziffer. Wertebereich [0,9]
     * @throws ProtocolException wenn die Ziffer nicht korrekt sesetzt werden konnte
     */
    void setSegmentValue(int nummer, int value) throws ProtocolException {
        if (nummer < 1 || nummer > 4)
            throw new IllegalArgumentException("nummer must be in [1,4]");
        byte byte1index = (byte)(nummer*2-1); // set high nibble (sequence number)
        byte byte2index = (byte)(nummer*2);   // set high nibble (sequence number)
        byte byte1, byte2;
        switch(value) { // Hier wird die bitweise Binärnotation verwendet.
            case 0: byte1 = 0b00001110; byte2 = 0b00001011; break;
            case 1: byte1 = 0b00000000; byte2 = 0b00001010; break;
            case 2: byte1 = 0b00001010; byte2 = 0b00001101; break;
            case 3: byte1 = 0b00001000; byte2 = 0b00001111; break;
            case 4: byte1 = 0b00000100; byte2 = 0b00001110; break;
            case 5: byte1 = 0b00001100; byte2 = 0b00000111; break;
            case 6: byte1 = 0b00001110; byte2 = 0b00000111; break;
            case 7: byte1 = 0b00001000; byte2 = 0b00001010; break;
            case 8: byte1 = 0b00001110; byte2 = 0b00001111; break;
            case 9: byte1 = 0b00001100; byte2 = 0b00001111; break;
            default:
                throw new IllegalArgumentException("value must be in [0,9]");
        }
        byte1 |= (byte1index<<4); // in high-Nibble schieben und Daten anhängen
        byte2 |= (byte2index<<4); // in high-Nibble schieben und Daten anhängen
        try {
            datagramm.setByte((int)byte1index, byte1);
            datagramm.setByte((int)byte2index, byte2);
        } catch(ProtocolException e) {
            throw e;
        }
    }

    /**
     * Gibt einen byte-Wert als Binärstring aus.
     * @param data
     * @return data als Binärstring
     */
    static String toBitString(Byte data) {
        String bs = Integer.toBinaryString(data);
        if(bs.length()>8) return bs.substring(bs.length()-8,bs.length());
        if(bs.length()<8) {
            for(int numzeros = 8-bs.length();numzeros>0;numzeros--) {bs = "0"+bs;}
            return bs;
        }
        return bs;
    };

    /**
     * Löscht alle Flags im internen Datenspeicher. Nur die Bytesequenznummer steht
     * dann jeweils im high-Nibble (oberste 4 Bit) aller Bytes in bytepuffer[]!
     */
    @BeforeEach
    public void reInitializeDatagramm(){
        if (datagramm == null)
            datagramm = new Datagramm();
        byte i = 0x00;    // hexadezimal für 0
        byte data = 0x00;
        try {
            for(i=0; i < 14; i++) {
                data = (byte)(i<<4);  // auf byte casten, da alle Bitoperationen einen int zurückgeben
                datagramm.setByte(i, data);
            }
        } catch(ProtocolException e) {
            System.out.println("reInitializeDatagramm: Exception "+e+" data="+Datagramm.toBitString(data));
            fail();
        }
    }
    /* Ab hier sind die Test-Methoden*/

    @BeforeEach
    public void create() {
        datagramm = new Datagramm();
    }
    // Erster Test
    /* zuerst wird mithilfe der setSegmentValue, den Wert 7 zugewiesen
     * und dann wird assertEquals benutzt, um zu uberpruefen, ob datagramm.getSegmentValue gleich
     * 7 ist.
     */
    @Test
    public void testGetSegmentValueMitDemWertSieben() throws ProtocolException {
        setSegmentValue(1, 7);
        boolean istGleichSieben = datagramm.getSegmentValue(1) == 7;
        assertTrue(istGleichSieben);
    }
    // Zweiter Test
    // zuerst wird mithilfe der setSegmentValue, den Wert 5 zugewiesen
    // und dann wird assertEquals benutzt, um zu uberpruefen, ob datagramm.getSegmentValue gleich
    // Wert 5 ist.
    @Test
    public void testGetSegmentValueMitDemWertFuenf() throws ProtocolException {
        setSegmentValue(2, 5);
        assertEquals(5, datagramm.getSegmentValue(2));
    }
    // Dritter Test
    // zuerst wird mithilfe der setSegmentValue, den Wert 2 zugewiesen
    // und dann wird assertEquals benutzt, um zu uberpruefen, ob das Objekt datagramm.getSegmentValue gleich
    // 2 ist.
    @Test
    public void testGetSegmentValueMitDemWertZwei() throws ProtocolException {
        setSegmentValue(3, 2);
        boolean istGleichZwei = datagramm.getSegmentValue(3) == 2;
        assertTrue(istGleichZwei);
    }
    // Vierter Test
    // zuerst wird mithilfe der setSegmentValue, den Wert 1 zugewiesen
    // und dann wird assertEquals benutzt, um zu uberpruefen, ob datagramm.getSegmentValue gleich
    // 1 ist.
    @Test
    public void testGetSegmentValueMitDemWertEins() throws ProtocolException {
        setSegmentValue(4, 1);
        assertEquals(1, datagramm.getSegmentValue(4));
    }
    // Fuenfter Test
    // zuerst wird mithilfe der setSegmentValue-Methode, den Wert 9 zugewiesen
    // und dann wird assertEquals benutzt, um zu uberpruefen, ob datagramm.getSegmentValue gleich
    // 9 ist.
    @Test
    public void testGetSegmentValueMitDemWertNeun() throws ProtocolException {
        setSegmentValue(4, 9);
        boolean istGleichNeun = datagramm.getSegmentValue(4) == 9;
        assertTrue(istGleichNeun);
    }
    /**
     * Ab hier fangen die Tests mit Exceptions an
     */
    // Sechster Test
    // Methode, die, testet die getSegmentValue mit einer negativen-Zahl
    // zuerst wird mithilfe der setSegmentValue-Methode, den Wert -1 zugewiesen
    //* und dann wird assertEquals benutzt, um zu uberpruefen, ob das Objekt datagramm.getSegmentValue gleich
    //* -1 ist.
    @Test
    public void testGetSegmentValueMitDemWertMinusEins() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> setSegmentValue(4, -1));
        assertEquals("value must be in [0,9]", exception.getMessage());
    }
    // Siebter Test
    // Diese Methode, testet ob, ein IllegalArgumentException geworfen wird,falls die Nummer 0 ist.
    // Die erste Linie innerhalb der Methode hat einen Lambda-Ausdruck
    // Die erwartete Nachricht fuer die Ausnahme(Exception) wird mit der aktuellen Nachricht mithilfe von assertEquals verglichen.
    @Test
    public void testGetSegmentValueFuerNummer0() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> datagramm.getSegmentValue(0));
        assertEquals("Param nummer must be in [1,4]", exception.getMessage());
    }
    // Achter Test
    // Diese Methode, testet ob, ein IllegalArgumentException geworfen wird,falls die Nummer 5 ist.
    // Die erste Linie innerhalb der Methode hat einen Lambda-Ausdruck
    // Die erwartete Nachricht fuer die Ausnahme(Exception) wird mit der aktuellen Nachricht mithilfe von assertEquals verglichen.
    @Test
    public void testGetSegmentValueMitNummer5() throws ProtocolException {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> datagramm.getSegmentValue(5));
        assertEquals("Param nummer must be in [1,4]", exception.getMessage());



    }
}