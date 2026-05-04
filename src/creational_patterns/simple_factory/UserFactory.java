package creational_patterns.simple_factory;

import models.*;

public class UserFactory {

    private UserFactory() {}

    public static User createUser(String role, String userId, String name,
                                  String email, String passwordHash, String extra) {
        if (role == null) throw new IllegalArgumentException("Role must not be null.");

        return switch (role.trim().toLowerCase()) {
            case "student"       -> new Student(userId, name, email, passwordHash, extra);
            case "trainer"       -> new Trainer(userId, name, email, passwordHash, extra);
            case "administrator" -> new Administrator(userId, name, email, passwordHash);
            default              -> throw new IllegalArgumentException(
                    "Unknown role: '" + role + "'. " +
                            "Expected: student | trainer | administrator");
        };
    }
}