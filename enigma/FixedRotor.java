package enigma;

/** Class that represents a rotor that has no ratchet and does not advance.
 *  @author Darwin Li
 */
class FixedRotor extends Rotor {

    /** Constructs the FixedRotor with two letters TPE is converted
     *  to an integer and SETT
     *  corresponding upper-case letter in the range A..Z. */
    public FixedRotor(int tpe, int sett) {
        super(tpe, sett);
    }

    @Override
    boolean advances() {
        return false;
    }

    @Override
    boolean atNotch() {
        return false;
    }

    /** Fixed rotors do not advance. */
    @Override
    void advance() {
    }

}
