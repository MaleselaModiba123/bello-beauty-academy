package repositories;

import models.Student;
import java.util.List;
import java.util.Optional;


public interface StudentRepository extends Repository<Student, String> {

    // Find a student by email — used for login and duplicate checks
    Optional<Student> findByEmail(String email);

    // Return all students with at least one active enrollment
    List<Student> findActiveStudents();
}