package exceptions;

// thrown when a student cannot be found by ID
public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String studentId) {
        super("Student not found with ID: " + studentId);
    }
}