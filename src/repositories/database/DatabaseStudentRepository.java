package repositories.database;

import models.Student;
import repositories.StudentRepository;

import java.util.List;
import java.util.Optional;

// Stub for a future database-backed implementation of StudentRepository
// Full implementation would use JDBC or JPA to connect to PostgreSQL
public class DatabaseStudentRepository implements StudentRepository {

    // Would use a JDBC connection pool to execute SQL queries
    @Override
    public void save(Student student) {
        throw new UnsupportedOperationException("Database storage not yet implemented.");
    }

    @Override
    public Optional<Student> findById(String id) {
        throw new UnsupportedOperationException("Database storage not yet implemented.");
    }

    @Override
    public List<Student> findAll() {
        throw new UnsupportedOperationException("Database storage not yet implemented.");
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Database storage not yet implemented.");
    }

    @Override
    public Optional<Student> findByEmail(String email) {
        throw new UnsupportedOperationException("Database storage not yet implemented.");
    }

    @Override
    public List<Student> findActiveStudents() {
        throw new UnsupportedOperationException("Database storage not yet implemented.");
    }
}