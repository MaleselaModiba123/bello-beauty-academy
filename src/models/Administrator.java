package models;

public class Administrator extends User {

    public Administrator(String userId, String name, String email, String passwordHash) {
        super(userId, name, email, passwordHash, UserRole.ADMINISTRATOR);
    }

    @Override
    public String getDashboardSummary() {
        return String.format("Admin Dashboard — %s | Full platform access", getName());
    }

    @Override
    public String toString() {
        return String.format("Administrator[id=%s, name=%s]", getUserId(), getName());
    }
}