// Lucas Walker
// AT CS
// Homework #12

public class Profile {
    private String username;
    private int storedHash;
    static int profileCounter = 0;

    public Profile(String username, int storedHash) {
        this.username = username;
        this.storedHash = storedHash;
        profileCounter++;
    }

    public void resetUsername(String newUsername) {
        username = newUsername;
    }

    public String getUsername() {
        return username;
    }

    public int getHash() {
        return storedHash;
    }

    public String toString() {
        return username + " -> " + storedHash;
    }
}