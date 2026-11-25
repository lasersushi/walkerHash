// Lucas Walker
// AT CS
// Homework #12

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Cracker {
    private int attemptCounter = 0;
    private int bruteForceLength;
    private ArrayList<Profile> profiles = new ArrayList<>();
    static final String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%";

    public String dictionaryAttack(String path) {
        // ArrayList<String> notGuessed = new ArrayList<>();
        ArrayList<String> cracked = new ArrayList<>();
        Scanner input;
        try {
            input = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            return "No file inputed";
        }
        while (input.hasNextLine()) {
            String line = input.nextLine();
            int hashed = line.hashCode();
            attemptCounter++;
            for (int i = 0; i < profiles.size(); i++) {
                Profile p = profiles.get(i);
                if (hashed == p.getHash()) {
                    cracked.add(p.getUsername() + " -> " + line);
                }
            }
        }
        return cracked.toString()
                + "\nIf there was something that wasn't guessed, consider using a brute force attack to guess it.";
    }

    public String bruteForce(int len) {
        bruteForceLength = len;
        ArrayList<String> cracked = new ArrayList<>();
        for (int i = 1; i <= bruteForceLength; i++) {
            generate("", i, cracked);
        }
        return cracked.toString();
    }

    private void generate(String guessSoFar, int targetLen, ArrayList<String> cracked) {
        if (guessSoFar.length() == targetLen) {
            int hashed = guessSoFar.hashCode();
            attemptCounter++;
            for (int i = 0; i < profiles.size(); i++) {
                Profile p = profiles.get(i);
                if (hashed == p.getHash()) {
                    cracked.add(p.getUsername() + " -> " + guessSoFar);
                }
            }
            return;
        }
        for (int i = 0; i < alphabet.length(); i++) {
            generate(guessSoFar + alphabet.charAt(i), targetLen, cracked);
        }
    }

    public void addEntry(String username, String password) {
        profiles.add(new Profile(username, hash(password)));
    }

    public static int hash(String unhashed) {
        return unhashed.hashCode();
    }

    public ArrayList<Profile> getProfiles() {
        return new ArrayList<>(profiles);
    }

    public int getAttempts() {
        return attemptCounter;
    }
}
