import java.util.Arrays;

public class RhetorischeMittel {

    // Spiegelwoerter sind Zeichenketten, die vorwaerts wie rueckwaerts gelesen identisch sind.
    boolean istSpiegelwort(String str) {
        // Eingabe in Kleinbuchstaben wandeln und 'white spaces' entfernen
        str = str.toLowerCase().replaceAll("\\s+", "");

        // Laenge des Strings speichern
        int length = str.length();

        //Auf nicht Alphanumerische Strings testen
        if (!str.matches("^([a-z0-9]+)?$")) {
            return false;
        }
        // Vergleich auf Zeichenebene
        for (int i = 0; i < length / 2; i++) {
            if (str.charAt(i) != str.charAt(length - i - 1)) {
                return false;
            }

        }
        return true;
    }

    // Schuettelwoerter sind zwei Zeichenkette, die dieselbe Laenge haben und bei denen eine Zeichenkette durch das Umstellen der Zeichen der anderen Zeichenkette entstanden ist.
    boolean sindSchuettelwoerter(String str1, String str2) {
        // Eingabe in Kleinbuchstaben wandeln und 'white spaces' entfernen
        str1 = str1.replaceAll("\\s+", "");
        str2 = str2.replaceAll("\\s+", "");

        if (str1.length() != str2.length()) {
            return false;

        } else {
            char[] ArraysS1 = str1.toLowerCase().toCharArray();
            char[] ArraysS2 = str2.toLowerCase().toCharArray();
            Arrays.sort(ArraysS1);
            Arrays.sort(ArraysS2);
            if (Arrays.equals(ArraysS1, ArraysS2)) {
                return true;
            } else {
                return false;
            }
        }
    }
}
