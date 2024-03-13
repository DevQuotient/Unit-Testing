public class Datagramm implements java.lang.Cloneable {
    private byte bytepuffer[] = new byte[14];

    Datagramm() { for(byte i=0; i < 14; i++) {this.bytepuffer[i]=((byte)(i<<4));} }

    void setByte(int index, byte wert) throws ProtocolException {
        if (index < 0 || index > 13) throw new IllegalArgumentException("Param index must be in [0,13]");
        byte bindex = (byte)(index << 4);
        byte bseqnr = (byte)(wert & 0xF0); // entferne Daten, behalte sequenznummer
        if (bindex != bseqnr) {
            System.out.println("bindex="+toBitString(bindex)+" bseqnr="+toBitString(bseqnr));
            throw new ProtocolException("High nibble of param value must fit param index");
        }
        bytepuffer[index] = wert;
    }

    int getSegmentValue(int nummer) throws ProtocolException {
        if (nummer < 1 || nummer > 4) throw new IllegalArgumentException("Param nummer must be in [1,4]");
        byte dataByte1 = bytepuffer[(nummer*2) - 1];
        byte dataByte2 = bytepuffer[nummer*2];
        byte segmentFlags = (byte) (dataByte1 << 4 | (dataByte2 & 0b00001111)); // Konkateniere high- und low-Nibble
        segmentFlags &= 0b11101111; // entferne Bit 5 (verwendet Vorzeichen/Dezimalpunkt)
        switch(segmentFlags){
            case (byte)0b11101011: return 0;
            case (byte)0b00001010: return 1;
            case (byte)0b10101101: return 2;
            case (byte)0b10001111: return 3;
            case (byte)0b01001110: return 4;
            case (byte)0b11000111: return 5;
            case (byte)0b11100111: return 6;
            case (byte)0b10001010: return 7;
            case (byte)0b11101111: return 8;
            case (byte)0b11001111: return 9;
            default:
                System.out.println("Unknown number " + toBitString(segmentFlags));
                throw new ProtocolException("Illegal digit segment code");
        }
    }

    @Override
    public Datagramm clone() {
        Datagramm myClone = new Datagramm();
        for(int i=0; i<14; i++) myClone.bytepuffer[i] = this.bytepuffer[i];
        return myClone;
    }

    static String toBitString(Byte data) {
        String bs = Integer.toBinaryString(data);
        if(bs.length()>8) return bs.substring(bs.length()-8,bs.length());
        if(bs.length()<8) {
            for(int numzeros = 8-bs.length();numzeros>0;numzeros--) {bs = "0"+bs;}
            return bs;
        }
        return bs;
    };

    @Override
    public String toString() {
        String result =  this.getClass().getName();
        if(bytepuffer == null) return result+": bytepuffer existiert nicht.";
        for (int i=0; i<14; i++) { result+="\n\tByte "+i+": "+toBitString(bytepuffer[i]);}
        return result+"\r";
    }
}

class ProtocolException extends java.lang.Exception {
    private static final long serialVersionUID = -7574688003048324454L;
    public ProtocolException() { super(); }
    public ProtocolException(String message) { super(message); }
}
