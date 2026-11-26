// Lucas Walker
// AT CS
// Homework #12

import java.io.File;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;
import java.util.*;

public class Cracker {
    private int attemptCounter = 0;
    private int bruteForceLength;
    private ArrayList<Profile> profiles = new ArrayList<>();
    static final String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%";

    public String dictionaryAttack(String path) {
        ArrayList<String> cracked = new ArrayList<>();
        Scanner input;
        try {
            input = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            return "No file inputed";
        }
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String hashed = hash(line);
            attemptCounter++;
            for (int i = 0; i < profiles.size(); i++) {
                Profile p = profiles.get(i);
                if (hashed.equals(p.getHash())) {
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
            String hashed = hash(guessSoFar);
            attemptCounter++;
            for (int i = 0; i < profiles.size(); i++) {
                Profile p = profiles.get(i);
                if (hashed.equals(p.getHash())) {
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
        if (!validInput(password)) {
            throw new IllegalArgumentException("Invalid password characters");
        }
        profiles.add(new Profile(username, hash(password)));
    }

    public static String hash(String unhashed) {
        try {
            MessageDigest digester = MessageDigest.getInstance("SHA-256");
            byte[] digest = digester.digest(unhashed.getBytes());
            String hex = HexFormat.of().formatHex(digest);
            return hex;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Profile> getProfiles() {
        return new ArrayList<>(profiles);
    }

    public String getAttempts() {
        NumberFormat formattingThingy = NumberFormat.getInstance();
        return formattingThingy.format(attemptCounter);
    }

    private boolean validInput(String password) {
        for (int i = 0; i < password.length(); i++) {
            String selected = password.substring(i, i++);
            if (alphabet.contains(selected)) {
                return false;
            }
        }
        return true;
    }
}
