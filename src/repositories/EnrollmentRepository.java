package repositories;

import models.Enrollment;
import models.EnrollmentStatus;
import java.util.List;

public interface EnrollmentRepository extends Repository<Enrollment, String> {

    // Get all enrollments for a specific student
    List<Enrollment> findByStudentId(String studentId);

    // Get all enrollments for a specific course
    List<Enrollment> findByCourseId(String courseId);

    // Filter enrollments by status — useful for the payment dashboard
    List<Enrollment> findByStatus(EnrollmentStatus status);
}