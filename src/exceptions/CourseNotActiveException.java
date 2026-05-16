package exceptions;

// thrown when a student tries to enroll in a course that is not active
public class CourseNotActiveException extends RuntimeException {
    public CourseNotActiveException(String courseId) {
        super("Course " + courseId + " is not active and cannot accept enrollments");
    }
}
