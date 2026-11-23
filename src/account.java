// Lucas Walker
// AT CS
// Homework #12

public class account {
    private String username;
    private String storedHash;

    public account(String username, String storedHash) {
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