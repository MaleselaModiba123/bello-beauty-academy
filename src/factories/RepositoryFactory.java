package factories;

import repositories.CertificateRepository;
import repositories.CourseRepository;
import repositories.EnrollmentRepository;
import repositories.StudentRepository;
import repositories.inmemory.InMemoryCertificateRepository;
import repositories.inmemory.InMemoryCourseRepository;
import repositories.inmemory.InMemoryEnrollmentRepository;
import repositories.inmemory.InMemoryStudentRepository;
import repositories.database.DatabaseStudentRepository;
import repositories.filesystem.FileSystemCourseRepository;

// Factory that returns the correct repository implementation based on storage type
// This decouples the rest of the system from knowing which backend is being used
public class RepositoryFactory {

    // Storage type constants
    public static final String MEMORY = "MEMORY";
    public static final String DATABASE = "DATABASE";
    public static final String FILESYSTEM = "FILESYSTEM";

    public static StudentRepository getStudentRepository(String storageType) {
        switch (storageType.toUpperCase()) {
            case MEMORY:     return new InMemoryStudentRepository();
            case DATABASE:   return new DatabaseStudentRepository();
            default: throw new IllegalArgumentException("Unknown storage type: " + storageType);
        }
    }

    public static CourseRepository getCourseRepository(String storageType) {
        switch (storageType.toUpperCase()) {
            case MEMORY:     return new InMemoryCourseRepository();
            case FILESYSTEM: return new FileSystemCourseRepository();
            default: throw new IllegalArgumentException("Unknown storage type: " + storageType);
        }
    }

    public static EnrollmentRepository getEnrollmentRepository(String storageType) {
        switch (storageType.toUpperCase()) {
            case MEMORY:  return new InMemoryEnrollmentRepository();
            default: throw new IllegalArgumentException("Unknown storage type: " + storageType);
        }
    }

    public static CertificateRepository getCertificateRepository(String storageType) {
        switch (storageType.toUpperCase()) {
            case MEMORY:  return new InMemoryCertificateRepository();
            default: throw new IllegalArgumentException("Unknown storage type: " + storageType);
        }
    }
}