package enigma;

/** Class that represents a rotor in the enigma machine.
 *  @author Darwin Li
 */
class Rotor {

    /** Constructs the Rotor with roman numeral TPE is converted
     *  to an integer, and the SETT
     *  corresponding upper-case letter in the range A..Z. */
    public Rotor(int tpe, int sett) {
        _tipe = tpe;
        _setting = sett;
    }

    /** Size of alphabet used for plaintext and ciphertext. */
    static final int ALPHABET_SIZE = 26;

    /** Assuming that P is an integer in the range 0..25, returns the
     *  corresponding upper-case letter in the range A..Z. */
    static char toLetter(int p) {
        return (char) (p + 'A');
    }

    /** Assuming that C is an upper-case letter in the range A-Z, return the
     *  corresponding index in the range 0..25. Inverse of toLetter. */
    static int toIndex(char c) {
        return (c - 'A');
    }

    /** Returns true iff this rotor has a ratchet and can advance. */
    boolean advances() {
        return true;
    }

    /** Returns the integer that represents type of THIS, will be 0-11. */
    int getType() {
        return _tipe;
    }

    /** Returns true iff this rotor has a left-to-right inverse. */
    boolean hasInverse() {
        return true;
    }

    /** Return my current rotational setting as an integer between 0
     *  and 25 (corresponding to letters 'A' to 'Z').  */
    int getSetting() {
        return _setting;
    }

    /** Set getSetting() to POSN.  */
    void set(int posn) {
        assert 0 <= posn && posn < ALPHABET_SIZE;
        _setting = posn;
    }

    /** Return the conversion of P (an integer in the range 0..25)
     *  according to my permutation. */
    int convertForward(int p) {
        int offset = (p + getSetting()) % ALPHABET_SIZE;
        int addSetting = toIndex(PermutationData.
            ROTOR_SPECS[getType()][1].charAt(offset));
        return ((addSetting - getSetting()) + ALPHABET_SIZE) % ALPHABET_SIZE;
    }

    /** Return the conversion of E (an integer in the range 0..25)
     *  according to the inverse of my permutation. */
    int convertBackward(int e) {
        int offset = (e + getSetting()) % ALPHABET_SIZE;
        int addSetting = toIndex(PermutationData.
            ROTOR_SPECS[getType()][2].charAt(offset));
        return ((addSetting - getSetting()) + ALPHABET_SIZE) % ALPHABET_SIZE;
    }

    /** Returns true iff I am positioned to allow the rotor to my left
     *  to advance. */
    boolean atNotch() {
        for (int i = 0; i < PermutationData.
            ROTOR_SPECS[getType()][3].length(); i = i + 1) {
            if (getSetting() == Rotor.toIndex(PermutationData.
                ROTOR_SPECS[getType()][3].charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /** Advance me one position. */
    void advance() {
        set((getSetting() + 1) % ALPHABET_SIZE);
    }

    /** My current setting (index 0..25, with 0 indicating that 'A'
     *  is showing). */
    private int _setting;

    /** My current tipe or type (index 0..11, with 0 indicating that 'I'
     *  is showing). */
    private int _tipe;
}
