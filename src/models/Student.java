package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Student extends User {

    private String phoneNumber;
    private final List<Enrollment> enrollments;

    public Student(String userId, String name, String email,
                   String passwordHash, String phoneNumber) {
        super(userId, name, email, passwordHash, UserRole.STUDENT);
        this.phoneNumber = phoneNumber;
        this.enrollments = new ArrayList<>();
    }

    public void addEnrollment(Enrollment enrollment) {
        if (enrollment == null) throw new IllegalArgumentException("Enrollment must not be null.");
        enrollments.add(enrollment);
    }

    // unmodifiableList prevents external code from mutating the internal list directly
    public List<Enrollment> getEnrollments() {
        return Collections.unmodifiableList(enrollments);
    }

    public int getActiveEnrollmentCount() {
        return (int) enrollments.stream()
                .filter(e -> e.getStatus() == EnrollmentStatus.ACTIVE)
                .count();
    }

    public String getPhoneNumber()             { return phoneNumber; }
    public void   setPhoneNumber(String phone) { this.phoneNumber = phone; }

    @Override
    public String getDashboardSummary() {
        return String.format("Student Dashboard — %s | Active Enrollments: %d",
                getName(), getActiveEnrollmentCount());
    }

    @Override
    public String toString() {
        return String.format("Student[id=%s, name=%s, phone=%s]",
                getUserId(), getName(), phoneNumber);
    }
}