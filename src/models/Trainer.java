package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Trainer extends User {

    private String bio;
    private final List<String> specialisations;

    public Trainer(String userId, String name, String email,
                   String passwordHash, String bio) {
        super(userId, name, email, passwordHash, UserRole.TRAINER);
        this.bio             = bio;
        this.specialisations = new ArrayList<>();
    }

    public void addSpecialisation(String specialisation) {
        if (specialisation != null && !specialisation.isBlank()) {
            specialisations.add(specialisation);
        }
    }

    public List<String> getSpecialisations() {
        return Collections.unmodifiableList(specialisations);
    }

    public String getBio()           { return bio; }
    public void   setBio(String bio) { this.bio = bio; }

    @Override
    public String getDashboardSummary() {
        return String.format("Trainer Dashboard — %s | Specialisations: %s",
                getName(), String.join(", ", specialisations));
    }

    @Override
    public String toString() {
        return String.format("Trainer[id=%s, name=%s, specialisations=%s]",
                getUserId(), getName(), specialisations);
    }
}