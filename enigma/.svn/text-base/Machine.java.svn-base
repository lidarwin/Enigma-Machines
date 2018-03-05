package enigma;

/** Class that represents a complete enigma machine.
 *  @author Darwin Li
 */
class Machine {

    /** Creates a set Rots[] called rot that will hold
     *  the rotors. */
    private Rotor[] rots = new Rotor[5];

    /** Constructs the set of Rotor of default value B, Beta, I,
     *  respectively and set at A. */
    Machine() {
        rots[0] = new Reflector(8, 0);
        rots[1] = new FixedRotor(10, 0);
        rots[2] = new Rotor(0, 0);
        rots[3] = new Rotor(0, 0);
        rots[4] = new Rotor(0, 0);
    }

    /** The getter for rots that returns a Rotor. */
    public Rotor getRots() {
        return rots[0];
    }

    /** Set my rotors to (from left to right) ROTORS.  Initially, the rotor
     *  settings are all 'A'. */
    void replaceRotors(Rotor[] rotors) {
        for (int i = 0; i < 5; i = i + 1) {
            rots[i] = rotors[i];
        }
    }

    /** Set my rotors according to SETTING, which must be a string of four
     *  upper-case letters. The first letter refers to the leftmost
     *  rotor setting.  */
    void setRotors(String setting) {
        for (int i = 0; i < 4; i = i + 1) {
            rots[i + 1].set((Rotor.toIndex(setting.charAt(i))));
        }
    }

    /** Returns the encoding/decoding of MSG, updating the state of
     *  the rotors accordingly. */
    String convert(String msg) {
        String line = msg;
        if (line.trim().isEmpty()) {
            return msg;
        }
        char[] output = new char[msg.length()];
        String message = new String();
        for (int i = 0; i < msg.length(); i = i + 1) {
            if (rots[3].atNotch()) {
                rots[3].advance();
                rots[2].advance();
                rots[4].advance();
            } else if (rots[4].atNotch()) {
                rots[3].advance();
                rots[4].advance();
            } else {
                rots[4].advance();
            }
            int signal = Rotor.toIndex(msg.charAt(i));
            for (int j = 4; j > -1; j = j - 1) {
                signal = rots[j].convertForward(signal);
            }
            for (int k = 1; k < 5; k = k + 1) {
                signal = rots[k].convertBackward(signal);
            }
            output[i] = Rotor.toLetter(signal);
            message = message + (output[i]);
        }
        return message;
    }
}
