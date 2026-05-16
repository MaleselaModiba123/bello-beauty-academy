package services;

import exceptions.CourseNotActiveException;
import exceptions.CourseNotFoundException;
import exceptions.DuplicateEnrollmentException;
import exceptions.EnrollmentNotFoundException;
import exceptions.StudentNotFoundException;
import models.Enrollment;
import models.EnrollmentStatus;
import repositories.CourseRepository;
import repositories.EnrollmentRepository;
import repositories.StudentRepository;

import java.util.List;

// handles all enrollment-related business logic
// uses three repositories — student, course, and enrollment
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    // constructor injection — all three repositories passed in
    public EnrollmentService(EnrollmentRepository enrollmentRepository,
                             StudentRepository studentRepository,
                             CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository    = studentRepository;
        this.courseRepository     = courseRepository;
    }

    // enroll a student in a course — validates student, course, and no duplicate
    public Enrollment enrollStudent(Enrollment enrollment) {
        if (enrollment == null) throw new IllegalArgumentException("Enrollment must not be null.");

        String studentId = enrollment.getStudent().getUserId();
        String courseId  = enrollment.getCourse().getCourseId();

        // confirm student exists
        studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));

        // confirm course exists
        courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(courseId));

        // confirm course is active
        if (!enrollment.getCourse().isActive()) {
            throw new CourseNotActiveException(courseId);
        }

        // check for duplicate enrollment
        boolean alreadyEnrolled = enrollmentRepository.findByStudentId(studentId)
                .stream()
                .anyMatch(e -> e.getCourse().getCourseId().equals(courseId)
                        && e.getStatus() != EnrollmentStatus.CANCELLED);
        if (alreadyEnrolled) {
            throw new DuplicateEnrollmentException(studentId, courseId);
        }

        enrollmentRepository.save(enrollment);
        return enrollment;
    }

    // find an enrollment by ID — throws if not found
    public Enrollment getEnrollmentById(String enrollmentId) {
        return enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new EnrollmentNotFoundException(enrollmentId));
    }

    // return all enrollments
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    // return all enrollments for a specific student
    public List<Enrollment> getEnrollmentsByStudent(String studentId) {
        studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));
        return enrollmentRepository.findByStudentId(studentId);
    }

    // activate an enrollment — only valid from PENDING status
    public Enrollment activateEnrollment(String enrollmentId) {
        Enrollment enrollment = getEnrollmentById(enrollmentId);
        if (enrollment.getStatus() != EnrollmentStatus.PENDING) {
            throw new IllegalStateException("Only PENDING enrollments can be activated. Current status: "
                    + enrollment.getStatus());
        }
        enrollment.activate();
        enrollmentRepository.save(enrollment);
        return enrollment;
    }

    // cancel an enrollment — cannot cancel a completed enrollment
    public void cancelEnrollment(String enrollmentId) {
        Enrollment enrollment = getEnrollmentById(enrollmentId);
        if (enrollment.getStatus() == EnrollmentStatus.COMPLETED) {
            throw new IllegalStateException("Cannot cancel a completed enrollment.");
        }
        enrollment.cancel();
        enrollmentRepository.save(enrollment);
    }
}
