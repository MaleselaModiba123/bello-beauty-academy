package services;

import exceptions.*;
import models.*;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import repositories.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EnrollmentServiceTest {

    private EnrollmentRepository enrollmentRepository;
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;
    private EnrollmentService enrollmentService;

    private Student student;
    private Course course;
    private Enrollment enrollment;

    @BeforeEach
    void setup() {
        enrollmentRepository = Mockito.mock(EnrollmentRepository.class);
        studentRepository    = Mockito.mock(StudentRepository.class);
        courseRepository     = Mockito.mock(CourseRepository.class);
        enrollmentService    = new EnrollmentService(enrollmentRepository, studentRepository, courseRepository);

        student    = new Student("S001", "Aaliyah Adams", "aaliyah@email.com", "hashed123", "0821234567");
        course     = new Course("C001", "Classic Lash Extensions", "desc", CourseCategory.LASH, 3, 1500.00);
        enrollment = new Enrollment("E001", student, course);
    }

    // enroll a student successfully
    @Test
    @Order(1)
    void testEnrollStudentSuccess() {
        when(studentRepository.findById("S001")).thenReturn(Optional.of(student));
        when(courseRepository.findById("C001")).thenReturn(Optional.of(course));
        when(enrollmentRepository.findByStudentId("S001")).thenReturn(List.of());

        Enrollment result = enrollmentService.enrollStudent(enrollment);
        assertEquals("E001", result.getEnrollmentId());
        verify(enrollmentRepository, times(1)).save(enrollment);
    }

    // enroll in inactive course — should throw CourseNotActiveException
    @Test
    @Order(2)
    void testEnrollInInactiveCourseThrows() {
        course.deactivate();
        when(studentRepository.findById("S001")).thenReturn(Optional.of(student));
        when(courseRepository.findById("C001")).thenReturn(Optional.of(course));
        assertThrows(CourseNotActiveException.class, () -> enrollmentService.enrollStudent(enrollment));
    }

    // duplicate enrollment — should throw DuplicateEnrollmentException
    @Test
    @Order(3)
    void testDuplicateEnrollmentThrows() {
        when(studentRepository.findById("S001")).thenReturn(Optional.of(student));
        when(courseRepository.findById("C001")).thenReturn(Optional.of(course));
        when(enrollmentRepository.findByStudentId("S001")).thenReturn(List.of(enrollment));

        Enrollment duplicate = new Enrollment("E002", student, course);
        assertThrows(DuplicateEnrollmentException.class, () -> enrollmentService.enrollStudent(duplicate));
    }

    // enroll with unknown student — should throw StudentNotFoundException
    @Test
    @Order(4)
    void testEnrollUnknownStudentThrows() {
        when(studentRepository.findById("S001")).thenReturn(Optional.empty());
        assertThrows(StudentNotFoundException.class, () -> enrollmentService.enrollStudent(enrollment));
    }

    // enroll with unknown course — should throw CourseNotFoundException
    @Test
    @Order(5)
    void testEnrollUnknownCourseThrows() {
        when(studentRepository.findById("S001")).thenReturn(Optional.of(student));
        when(courseRepository.findById("C001")).thenReturn(Optional.empty());
        assertThrows(CourseNotFoundException.class, () -> enrollmentService.enrollStudent(enrollment));
    }

    // null enrollment should throw IllegalArgumentException
    @Test
    @Order(6)
    void testEnrollNullThrows() {
        assertThrows(IllegalArgumentException.class, () -> enrollmentService.enrollStudent(null));
    }

    // find an enrollment by ID successfully
    @Test
    @Order(7)
    void testGetEnrollmentByIdSuccess() {
        when(enrollmentRepository.findById("E001")).thenReturn(Optional.of(enrollment));
        Enrollment result = enrollmentService.getEnrollmentById("E001");
        assertEquals("E001", result.getEnrollmentId());
    }

    // find an enrollment that does not exist — should throw EnrollmentNotFoundException
    @Test
    @Order(8)
    void testGetEnrollmentByIdNotFound() {
        when(enrollmentRepository.findById("UNKNOWN")).thenReturn(Optional.empty());
        assertThrows(EnrollmentNotFoundException.class,
                () -> enrollmentService.getEnrollmentById("UNKNOWN"));
    }

    // get all enrollments
    @Test
    @Order(9)
    void testGetAllEnrollments() {
        when(enrollmentRepository.findAll()).thenReturn(List.of(enrollment));
        List<Enrollment> results = enrollmentService.getAllEnrollments();
        assertEquals(1, results.size());
    }

    // get enrollments by student ID
    @Test
    @Order(10)
    void testGetEnrollmentsByStudent() {
        when(studentRepository.findById("S001")).thenReturn(Optional.of(student));
        when(enrollmentRepository.findByStudentId("S001")).thenReturn(List.of(enrollment));
        List<Enrollment> results = enrollmentService.getEnrollmentsByStudent("S001");
        assertEquals(1, results.size());
    }

    // get enrollments for unknown student — should throw StudentNotFoundException
    @Test
    @Order(11)
    void testGetEnrollmentsByUnknownStudentThrows() {
        when(studentRepository.findById("UNKNOWN")).thenReturn(Optional.empty());
        assertThrows(StudentNotFoundException.class,
                () -> enrollmentService.getEnrollmentsByStudent("UNKNOWN"));
    }

    // activate a pending enrollment
    @Test
    @Order(12)
    void testActivateEnrollmentSuccess() {
        when(enrollmentRepository.findById("E001")).thenReturn(Optional.of(enrollment));
        Enrollment result = enrollmentService.activateEnrollment("E001");
        assertEquals(EnrollmentStatus.ACTIVE, result.getStatus());
        verify(enrollmentRepository, times(1)).save(enrollment);
    }

    // activate an already active enrollment — should throw IllegalStateException
    @Test
    @Order(13)
    void testActivateAlreadyActiveEnrollmentThrows() {
        enrollment.activate();
        when(enrollmentRepository.findById("E001")).thenReturn(Optional.of(enrollment));
        assertThrows(IllegalStateException.class,
                () -> enrollmentService.activateEnrollment("E001"));
    }

    // cancel an enrollment
    @Test
    @Order(14)
    void testCancelEnrollmentSuccess() {
        when(enrollmentRepository.findById("E001")).thenReturn(Optional.of(enrollment));
        enrollmentService.cancelEnrollment("E001");
        verify(enrollmentRepository, times(1)).save(enrollment);
    }

    // cancel a completed enrollment — should throw IllegalStateException
    @Test
    @Order(15)
    void testCancelCompletedEnrollmentThrows() {
        enrollment.activate();
        enrollment.complete();
        when(enrollmentRepository.findById("E001")).thenReturn(Optional.of(enrollment));
        assertThrows(IllegalStateException.class,
                () -> enrollmentService.cancelEnrollment("E001"));
    }
}