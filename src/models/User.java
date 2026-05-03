package models;

/**
 * Abstract base class representing a platform user.
 * Implements the Open/Closed Principle — open for extension (Student, Trainer, Admin),
 * closed for modification of core identity logic.
 */
public abstract class User {

    private final String userId;
    private String name;
    private String email;
    private String passwordHash;
    private final UserRole role;
    private boolean active;

    public User(String userId, String name, String email, String passwordHash, UserRole role) {
        if (userId == null || userId.isBlank()) throw new IllegalArgumentException("userId must not be blank.");
        if (email == null || !email.contains("@")) throw new IllegalArgumentException("Invalid email address.");
        this.userId       = userId;
        this.name         = name;
        this.email        = email;
        this.passwordHash = passwordHash;
        this.role         = role;
        this.active       = true;
    }


    public String   getUserId()       { return userId; }
    public String   getName()         { return name; }
    public String   getEmail()        { return email; }
    public String   getPasswordHash() { return passwordHash; }
    public UserRole getRole()         { return role; }
    public boolean  isActive()        { return active; }


    public void setName(String name)                 { this.name = name; }
    public void setEmail(String email)               { this.email = email; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public void setActive(boolean active)            { this.active = active; }


    // Returns a human-readable summary of this user's dashboard content. */
    public abstract String getDashboardSummary();

    @Override
    public String toString() {
        return String.format("User[id=%s, name=%s, role=%s, active=%b]",
                userId, name, role, active);
    }
}