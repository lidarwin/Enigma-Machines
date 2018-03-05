package enigma;

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/** Enigma simulator.
 *  @author Darwin Li
 */
public final class Main {

    /** Process a sequence of encryptions and decryptions, as
     *  specified in the input from the standard input.  Print the
     *  results on the standard output. Exits normally if there are
     *  no errors in the input; otherwise with code 1. */
    public static void main(String[] unused) throws IOException {
        Machine M;
        BufferedReader input =
            new BufferedReader(new InputStreamReader(System.in));

        buildMap();

        M = null;

        try {
            while (true) {
                String line = input.readLine();
                if (line == null) {
                    break;
                }
                if (isConfigurationLine(line)) {
                    M = new Machine();
                    configure(M, line);
                } else if (M == null) {
                    throw new IOException("No config exists nor recognized.");
                } else if (line.trim().isEmpty()) {
                    System.out.println();
                } else {
                    printMessageLine(M.convert(standardize(line)));
                }
            }
        } catch (IOException excp) {
            System.err.printf("Input error: %s%n", excp.getMessage());
            System.exit(1);
        }
    }

    /** Return true iff LINE is an Enigma configuration line. */
    private static boolean isConfigurationLine(String line) throws IOException {
        String[] rfl = {"B", "C"};
        String[] non = {"BETA", "GAMMA"};
        String[] rot = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII"};
        String[] arr = line.split("\\s+");
        if (!arr[0].equals("*")) {
            return false;
        }
        if (arr.length != 7) {
            throw new IOException("Not Config");
        }
        if (arr[0].length() > 1) {
            throw new IOException("Not Config");
        }
        if (arr[1].equals(rfl[0]) || arr[1].equals(rfl[1])) {
            if (arr[2].equals(non[0]) || arr[2].equals(non[1])) {
                for (int i = 0; i < 8; i = i + 1) {
                    if (arr[3].equals(rot[i])) {
                        for (int j = 0; j < 8; j = j + 1) {
                            if (arr[4].equals(rot[i])) {
                                throw new IOException("Need config");
                            }
                            if (arr[4].equals(rot[j])) {
                                for (int k = 0; k < 8; k = k + 1) {
                                    if (arr[5].equals(rot[i])) {
                                        throw new IOException("Need config");
                                    }
                                    if (arr[5].equals(rot[j])) {
                                        throw new IOException("Need config");
                                    }
                                    if (arr[5].equals(rot[k])) {
                                        if (arr[6].matches("[A-Z]{4}")) {
                                            return true;
                                        }
                                        throw new IOException("Need config");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new IOException("Need proper config");
    }

    /** Configure M according to the specification given on CONFIG,
     *  which must have the format specified in the assignment. */
    private static void configure(Machine M, String config) {
        Rotor[] newRots = new Rotor[5];
        String[] arr = config.split("\\s+", 7);
        for (int i = 1; i < 6; i = i + 1) {
            newRots[i - 1] = new Rotor(rf.get(arr[i]), 0);
        }
        M.replaceRotors(newRots);
        M.setRotors(arr[6]);
    }

    /** Return the result of converting LINE to all upper case,
     *  removing all blanks and tabs.  It is an error if LINE contains
     *  characters other than letters and blanks. */
    private static String standardize(String line) throws IOException {
        String str = line;
        str = str.replaceAll("\\s+", "");
        str = str.replaceAll("\t+", "");
        str = str.toUpperCase();
        if (str.matches(".*\\d.*")) {
            throw new IOException("Contains numeric characters!");
        }
        return str;
    }

    /** Print MSG in groups of five (except that the last group may
     *  have fewer letters). */
    private static void printMessageLine(String msg) {
        String line = new String();
        String[] arr = msg.split("(?<=\\G.{5})");
        for (int i = 0; i < arr.length; i = i + 1) {
            line = line + arr[i] + " ";
        }
        System.out.println(line);
    }

    /** Return the key value for the rf HashMap. */
    public int keyGetter() {
        return rf.get("I");
    }

    /** HashMap mapping of each Rotor type to its index in PermutationData. */
    private static HashMap<String, Integer> rf = new HashMap<String, Integer>();

    /** Create the mapping to all the necessary rotors. */
    private static void buildMap() {
        rf.put("I", 0);
        rf.put("II", 1);
        rf.put("III", 2);
        rf.put("IV", 3);
        rf.put("V", 4);
        rf.put("VI", 5);
        rf.put("VII", 6);
        rf.put("VIII", 7);
        rf.put("BETA", 8);
        rf.put("GAMMA", 9);
        rf.put("B", 10);
        rf.put("C", rf.get("B") + 1);
    }

}

