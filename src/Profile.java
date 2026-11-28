// Lucas Walker
// AT CS
// Homework #12

/**
 * Profile represents one stored account: a username and the hash of its
 * password.
 */
public class Profile {
    private String username;
    private String storedHash;
    static int profileCounter = 0;

    /**
     * Creates a new profile for the given user.
     * @param username   is the user's given username
     * @param storedHash is a hashed version of the user's password
     */
    public Profile(String username, String storedHash) {
        this.username = username;
        this.storedHash = storedHash;
        profileCounter++;
    }

    /**
     * Resets the username of a Profile object
     * 
     * @param newUsername is the Profile object's new username
     */
    public void resetUsername(String newUsername) {
        username = newUsername;
    }

    /**
     * @return the username of a Profile object
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the hash of a Profile object
     */
    public String getHash() {
        return storedHash;
    }

    /**
     * @return The stored hash of a Profile object and also that object's username
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return username + " -> " + storedHash;
    }
}