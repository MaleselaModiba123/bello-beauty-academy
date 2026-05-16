package exceptions;

// thrown when an enrollment cannot be found by ID
public class EnrollmentNotFoundException extends RuntimeException {
    public EnrollmentNotFoundException(String enrollmentId) {
        super("Enrollment not found with ID: " + enrollmentId);
    }
}
