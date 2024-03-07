import static java.lang.Math.log;

/*
 Die Logit-k Klasse berechnet die Logit-Funktion. in der Methode "calc" und
 wirft eine IllegalArgumentException bei der Eingabe von ungeeigneten Werten */

public class Logit {
    public double calc(double in) throws IllegalArgumentException {
        double out;
        if ((in <= 0.0d) || (in >= 1.0d)) {
            throw new IllegalArgumentException("Waehlen Sie einen Wert zwischen 0 und 1");
        }
        out = log(in / (1 - in));
        return (out);
    }
    // f(x) = ln(x/1-x);
    /* Hierbei ist ln(x) der natuerliche LOgarithmus von x. Es ist zu beachten, dass f(x) nur fuer x zwischen 0 und 1 definiert ist(ohne die Grenzen des Intervalls). Die folgende Tabelle gibt einige Funktionswerte im genannten Intervall an.
     x = 0.1; y= -2.197224577336219
     x = 0.3; y= -0.8472978603872036
     x = 0.5; y= 0.0
     x = 0.7; y= 0.8472978603872036
     x = 0.9; y= 2.197224577336219
     */
}
