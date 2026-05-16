package exceptions;

// thrown when a student tries to enroll in a course they are already enrolled in
public class DuplicateEnrollmentException extends RuntimeException {
    public DuplicateEnrollmentException(String studentId, String courseId) {
        super("Student " + studentId + " is already enrolled in course " + courseId);
    }
}
