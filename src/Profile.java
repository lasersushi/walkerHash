// Lucas Walker
// AT CS
// Homework #12

public class Profile {
    private String username;
    private String storedHash;
    static int profileCounter = 0;

    public Profile(String username, String storedHash) {
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

    public String getHash() {
        return storedHash;
    }

    public String toString() {
        return username + " -> " + storedHash;
    }
}