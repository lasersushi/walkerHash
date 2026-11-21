// Lucas Walker
// AT CS
// Homework #12

public class PasswordEntry {
    private String username;
    private String storedHash;

    public PasswordEntry(String username, String storedHash) {
        this.username = username;
        this.storedHash = storedHash;
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
}