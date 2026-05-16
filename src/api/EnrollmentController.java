package api;

import exceptions.CourseNotActiveException;
import exceptions.DuplicateEnrollmentException;
import exceptions.EnrollmentNotFoundException;
import exceptions.StudentNotFoundException;
import models.Enrollment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.EnrollmentService;

import java.util.List;

// thin controller — all business logic stays in EnrollmentService
@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    // constructor injection
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    // GET /api/enrollments — return all enrollments
    @GetMapping
    public ResponseEntity<List<Enrollment>> getAllEnrollments() {
        return ResponseEntity.ok(enrollmentService.getAllEnrollments());
    }

    // GET /api/enrollments/{id} — return an enrollment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable String id) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentById(id));
    }

    // GET /api/enrollments/student/{studentId} — return all enrollments for a student
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByStudent(@PathVariable String studentId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByStudent(studentId));
    }

    // POST /api/enrollments — enroll a student in a course
    @PostMapping
    public ResponseEntity<Enrollment> enrollStudent(@RequestBody Enrollment enrollment) {
        Enrollment created = enrollmentService.enrollStudent(enrollment);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT /api/enrollments/{id}/activate — activate an enrollment
    @PutMapping("/{id}/activate")
    public ResponseEntity<Enrollment> activateEnrollment(@PathVariable String id) {
        return ResponseEntity.ok(enrollmentService.activateEnrollment(id));
    }

    // DELETE /api/enrollments/{id} — cancel an enrollment
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelEnrollment(@PathVariable String id) {
        enrollmentService.cancelEnrollment(id);
        return ResponseEntity.noContent().build();
    }

    // handle EnrollmentNotFoundException — returns 404
    @ExceptionHandler(EnrollmentNotFoundException.class)
    public ResponseEntity<String> handleNotFound(EnrollmentNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // handle StudentNotFoundException — returns 404
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<String> handleStudentNotFound(StudentNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // handle DuplicateEnrollmentException — returns 409
    @ExceptionHandler(DuplicateEnrollmentException.class)
    public ResponseEntity<String> handleDuplicate(DuplicateEnrollmentException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    // handle CourseNotActiveException — returns 400
    @ExceptionHandler(CourseNotActiveException.class)
    public ResponseEntity<String> handleCourseNotActive(CourseNotActiveException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // handle IllegalStateException — returns 409
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleConflict(IllegalStateException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}