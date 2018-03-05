package enigma;

/** Class that represents a reflector in the enigma.
 *  @author Darwin Li
 */
class Reflector extends Rotor {

    /** Constructs the Refector with two letters TPE is converted
     *  to an integer and SETT
     *  which does nothing for reflectors. */
    public Reflector(int tpe, int sett) {
        super(tpe, sett);
    }

    /** Returns whether this is not a Reflector or not. */
    @Override
    boolean hasInverse() {
        return false;
    }

    /** Takes in P to return the Reflector's version of convertForward. */
    @Override
    int convertForward(int p) {
        int offset = (p + getSetting()) % ALPHABET_SIZE;
        int addSetting = toIndex(PermutationData.
            ROTOR_SPECS[getType()][1].charAt(offset));
        return ((addSetting - getSetting()) + ALPHABET_SIZE) % ALPHABET_SIZE;
    }

    /** Returns a useless value; should never be called. */
    @Override
    int convertBackward(int unused) {
        throw new UnsupportedOperationException();
    }

}
