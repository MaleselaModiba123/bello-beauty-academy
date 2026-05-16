package exceptions;

// thrown when a course cannot be found by ID
public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(String courseId) {
        super("Course not found with ID: " + courseId);
    }
}
