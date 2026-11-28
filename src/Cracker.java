// Lucas Walker
// AT CS
// Homework #12

import java.io.File;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HexFormat;
import java.util.Scanner;

/**
 * This class cracks hashes and hashes strings.
 */
public class Cracker {
    private int attemptCounter = 0;
    private int bruteForceLength;
    private ArrayList<Profile> profiles = new ArrayList<>();
    static final String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%";

    /**
     * 
     * @param path which is the file path. If there is no path inputed, it returns
     *             an error message.
     * @return All of the cracked passwords of every Profile as well as each
     *         profile's username
     */
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

    /**
     * This method guesses every possible password in order to find the correct
     * hash.
     * 
     * @param len which is the max length of the given password for the algorithm to
     *            guess
     * @return A String with the cracked password of each user.
     */
    public String bruteForce(int len) {
        bruteForceLength = len;
        ArrayList<String> cracked = new ArrayList<>();
        for (int i = 1; i <= bruteForceLength; i++) {
            generate("", i, cracked);
        }
        return cracked.toString();
    }

    /**
     * This method guesses every guess of target length until it gets something
     * 
     * @param guessSoFar is the guess so far for the method
     * @param targetLen  This length is determined by the for loop in the
     *                   {@link #bruteForce} method
     * @param cracked    Once you find something, add it to this arraylist
     */
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

    /**
     * This method creates a profile object and add it to the ArrayList {@link #profiles} which contains every profile
     * @param username of the profile object you wish to create
     * @param password of the profile object you wish to create(unhashed string)
     * @throws IllegalArgumentExcpetion if the password contains invalid characters
     */
    public void addEntry(String username, String password) {
        if (!validInput(password)) {
            throw new IllegalArgumentException("Invalid password characters");
        }
        profiles.add(new Profile(username, hash(password)));
    }

    /**
     * This method uses SHA-256 to hash a String. SHA-256 was used here because the regular {@link #hashCode()} method had collisions.
     * @param unhashed asks for the unhashed string of the password
     * @return a hashed version of the inputed password.
     */
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

    /**
     * @return a copy of the {@link #profiles} Arraylist
     */
    public ArrayList<Profile> getProfiles() {
        return new ArrayList<>(profiles);
    }

    /**
     * @return the number of times the program attempted to guess your password. Returns it in a formatted form(meaning with commas) using {@link NumberFormat}
     */
    public String getAttempts() {
        NumberFormat formattingThingy = NumberFormat.getInstance();
        return formattingThingy.format(attemptCounter);
    }

    /**
     * This method checks your password against the valid characters to see if the password can be used
     * @param password asks for the inputed password
     * @return A boolean showing whether or not the password is good to be used
     */
    private boolean validInput(String password) {
        for (int i = 0; i < password.length(); i++) {
            String selected = password.substring(i, i + 1);
            if (!alphabet.contains(selected)) {
                return false;
            }
        }
        return true;
    }
}
