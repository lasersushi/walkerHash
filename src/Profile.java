// Lucas Walker
// AT CS
// Homework #12

public class Profile {
    private String username;
    private String storedHash;
    static int profileCounter = 0;

    public Profile(String username, String storedHash) { // Profile constructor, assigns values to username and also the
                                                         // hash
        this.username = username;
        this.storedHash = storedHash;
        profileCounter++; // Increments profile counter everytime the porfile constructor is used
    }
    //TODO: Find usage for resetUsername
    public void resetUsername(String newUsername) { // Resets the username of a profile
        username = newUsername;
    }

    public String getUsername() { // Gets the username instance variable
        return username;
    }

    public String getHash() { // Gets the hash instance variable
        return storedHash;
    }

    public String toString() {
        return username + " -> " + storedHash; // To string method which returns the username and the stored hash(not
                                               // the password)
    }
}