import models.*;
import repositories.inmemory.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestInMemoryRepositories {

    // ─── shared test data ───────────────────────────────────────────────────

    private static Student student;
    private static Course course;
    private static Enrollment enrollment;
    private static Certificate certificate;

    @BeforeAll
    static void setup() {
        student = new Student("S001", "Aaliyah Adams", "aaliyah@email.com", "hashed123", "0821234567");
        course  = new Course("C001", "Classic Lash Extensions", "Learn classic lash application",
                CourseCategory.LASH, 3, 1500.00);
        enrollment  = new Enrollment("E001", student, course);
        certificate = new Certificate("CERT001", student, course, "BBA-2026-0001", "/certs/CERT001.pdf");
    }

    // ─── StudentRepository tests ─────────────────────────────────────────────

    @Test
    @Order(1)
    void testSaveAndFindStudentById() {
        InMemoryStudentRepository repo = new InMemoryStudentRepository();
        repo.save(student);
        assertTrue(repo.findById("S001").isPresent());
        assertEquals("Aaliyah Adams", repo.findById("S001").get().getName());
    }

    @Test
    @Order(2)
    void testFindStudentByIdNotFound() {
        InMemoryStudentRepository repo = new InMemoryStudentRepository();
        assertFalse(repo.findById("UNKNOWN").isPresent());
    }

    @Test
    @Order(3)
    void testFindStudentByIdNullReturnsEmpty() {
        InMemoryStudentRepository repo = new InMemoryStudentRepository();
        assertFalse(repo.findById(null).isPresent());
    }

    @Test
    @Order(4)
    void testFindAllStudentsEmpty() {
        InMemoryStudentRepository repo = new InMemoryStudentRepository();
        assertTrue(repo.findAll().isEmpty());
    }

    @Test
    @Order(5)
    void testFindAllStudentsPopulated() {
        InMemoryStudentRepository repo = new InMemoryStudentRepository();
        repo.save(student);
        assertEquals(1, repo.findAll().size());
    }

    @Test
    @Order(6)
    void testDeleteStudent() {
        InMemoryStudentRepository repo = new InMemoryStudentRepository();
        repo.save(student);
        repo.delete("S001");
        assertFalse(repo.findById("S001").isPresent());
    }

    @Test
    @Order(7)
    void testDeleteNonExistentStudentDoesNotThrow() {
        InMemoryStudentRepository repo = new InMemoryStudentRepository();
        assertDoesNotThrow(() -> repo.delete("UNKNOWN"));
    }

    @Test
    @Order(8)
    void testSaveNullStudentThrows() {
        InMemoryStudentRepository repo = new InMemoryStudentRepository();
        assertThrows(IllegalArgumentException.class, () -> repo.save(null));
    }

    @Test
    @Order(9)
    void testFindStudentByEmail() {
        InMemoryStudentRepository repo = new InMemoryStudentRepository();
        repo.save(student);
        assertTrue(repo.findByEmail("aaliyah@email.com").isPresent());
    }

    @Test
    @Order(10)
    void testFindStudentByEmailNotFound() {
        InMemoryStudentRepository repo = new InMemoryStudentRepository();
        assertFalse(repo.findByEmail("unknown@email.com").isPresent());
    }

    @Test
    @Order(11)
    void testFindStudentByEmailNull() {
        InMemoryStudentRepository repo = new InMemoryStudentRepository();
        assertFalse(repo.findByEmail(null).isPresent());
    }

    @Test
    @Order(12)
    void testSaveDuplicateStudentOverwrites() {
        InMemoryStudentRepository repo = new InMemoryStudentRepository();
        repo.save(student);
        repo.save(student);
        assertEquals(1, repo.findAll().size());
    }

    // ─── CourseRepository tests ──────────────────────────────────────────────

    @Test
    @Order(13)
    void testSaveAndFindCourseById() {
        InMemoryCourseRepository repo = new InMemoryCourseRepository();
        repo.save(course);
        assertTrue(repo.findById("C001").isPresent());
        assertEquals("Classic Lash Extensions", repo.findById("C001").get().getTitle());
    }

    @Test
    @Order(14)
    void testFindCourseByIdNotFound() {
        InMemoryCourseRepository repo = new InMemoryCourseRepository();
        assertFalse(repo.findById("UNKNOWN").isPresent());
    }

    @Test
    @Order(15)
    void testFindAllCoursesEmpty() {
        InMemoryCourseRepository repo = new InMemoryCourseRepository();
        assertTrue(repo.findAll().isEmpty());
    }

    @Test
    @Order(16)
    void testDeleteCourse() {
        InMemoryCourseRepository repo = new InMemoryCourseRepository();
        repo.save(course);
        repo.delete("C001");
        assertFalse(repo.findById("C001").isPresent());
    }

    @Test
    @Order(17)
    void testDeleteNonExistentCourseDoesNotThrow() {
        InMemoryCourseRepository repo = new InMemoryCourseRepository();
        assertDoesNotThrow(() -> repo.delete("UNKNOWN"));
    }

    @Test
    @Order(18)
    void testSaveNullCourseThrows() {
        InMemoryCourseRepository repo = new InMemoryCourseRepository();
        assertThrows(IllegalArgumentException.class, () -> repo.save(null));
    }

    @Test
    @Order(19)
    void testFindActiveCourses() {
        InMemoryCourseRepository repo = new InMemoryCourseRepository();
        repo.save(course);
        List<Course> active = repo.findActiveCourses();
        assertEquals(1, active.size());
    }

    @Test
    @Order(20)
    void testFindCoursesByCategory() {
        InMemoryCourseRepository repo = new InMemoryCourseRepository();
        repo.save(course);
        List<Course> lashCourses = repo.findByCategory(CourseCategory.LASH);
        assertEquals(1, lashCourses.size());
    }

    @Test
    @Order(21)
    void testFindCoursesByCategoryNoMatch() {
        InMemoryCourseRepository repo = new InMemoryCourseRepository();
        repo.save(course);
        List<Course> makeupCourses = repo.findByCategory(CourseCategory.MAKEUP);
        assertTrue(makeupCourses.isEmpty());
    }

    @Test
    @Order(22)
    void testFindCoursesByCategoryNull() {
        InMemoryCourseRepository repo = new InMemoryCourseRepository();
        repo.save(course);
        assertTrue(repo.findByCategory(null).isEmpty());
    }

    // ─── EnrollmentRepository tests ──────────────────────────────────────────

    @Test
    @Order(23)
    void testSaveAndFindEnrollmentById() {
        InMemoryEnrollmentRepository repo = new InMemoryEnrollmentRepository();
        repo.save(enrollment);
        assertTrue(repo.findById("E001").isPresent());
    }

    @Test
    @Order(24)
    void testFindEnrollmentByIdNotFound() {
        InMemoryEnrollmentRepository repo = new InMemoryEnrollmentRepository();
        assertFalse(repo.findById("UNKNOWN").isPresent());
    }

    @Test
    @Order(25)
    void testFindAllEnrollmentsEmpty() {
        InMemoryEnrollmentRepository repo = new InMemoryEnrollmentRepository();
        assertTrue(repo.findAll().isEmpty());
    }

    @Test
    @Order(26)
    void testDeleteEnrollment() {
        InMemoryEnrollmentRepository repo = new InMemoryEnrollmentRepository();
        repo.save(enrollment);
        repo.delete("E001");
        assertFalse(repo.findById("E001").isPresent());
    }

    @Test
    @Order(27)
    void testDeleteNonExistentEnrollmentDoesNotThrow() {
        InMemoryEnrollmentRepository repo = new InMemoryEnrollmentRepository();
        assertDoesNotThrow(() -> repo.delete("UNKNOWN"));
    }

    @Test
    @Order(28)
    void testSaveNullEnrollmentThrows() {
        InMemoryEnrollmentRepository repo = new InMemoryEnrollmentRepository();
        assertThrows(IllegalArgumentException.class, () -> repo.save(null));
    }

    @Test
    @Order(29)
    void testFindEnrollmentByStudentId() {
        InMemoryEnrollmentRepository repo = new InMemoryEnrollmentRepository();
        repo.save(enrollment);
        List<Enrollment> results = repo.findByStudentId("S001");
        assertEquals(1, results.size());
    }

    @Test
    @Order(30)
    void testFindEnrollmentByStudentIdNull() {
        InMemoryEnrollmentRepository repo = new InMemoryEnrollmentRepository();
        assertTrue(repo.findByStudentId(null).isEmpty());
    }

    @Test
    @Order(31)
    void testFindEnrollmentByCourseId() {
        InMemoryEnrollmentRepository repo = new InMemoryEnrollmentRepository();
        repo.save(enrollment);
        List<Enrollment> results = repo.findByCourseId("C001");
        assertEquals(1, results.size());
    }

    @Test
    @Order(32)
    void testFindEnrollmentByStatus() {
        InMemoryEnrollmentRepository repo = new InMemoryEnrollmentRepository();
        repo.save(enrollment);
        // enrollment starts as PENDING
        List<Enrollment> pending = repo.findByStatus(EnrollmentStatus.PENDING);
        assertEquals(1, pending.size());
    }

    @Test
    @Order(33)
    void testFindEnrollmentByStatusNull() {
        InMemoryEnrollmentRepository repo = new InMemoryEnrollmentRepository();
        assertTrue(repo.findByStatus(null).isEmpty());
    }

    // ─── CertificateRepository tests ─────────────────────────────────────────

    @Test
    @Order(34)
    void testSaveAndFindCertificateById() {
        InMemoryCertificateRepository repo = new InMemoryCertificateRepository();
        repo.save(certificate);
        assertTrue(repo.findById("CERT001").isPresent());
    }

    @Test
    @Order(35)
    void testFindCertificateByIdNotFound() {
        InMemoryCertificateRepository repo = new InMemoryCertificateRepository();
        assertFalse(repo.findById("UNKNOWN").isPresent());
    }

    @Test
    @Order(36)
    void testFindAllCertificatesEmpty() {
        InMemoryCertificateRepository repo = new InMemoryCertificateRepository();
        assertTrue(repo.findAll().isEmpty());
    }

    @Test
    @Order(37)
    void testDeleteCertificate() {
        InMemoryCertificateRepository repo = new InMemoryCertificateRepository();
        repo.save(certificate);
        repo.delete("CERT001");
        assertFalse(repo.findById("CERT001").isPresent());
    }

    @Test
    @Order(38)
    void testDeleteNonExistentCertificateDoesNotThrow() {
        InMemoryCertificateRepository repo = new InMemoryCertificateRepository();
        assertDoesNotThrow(() -> repo.delete("UNKNOWN"));
    }

    @Test
    @Order(39)
    void testSaveNullCertificateThrows() {
        InMemoryCertificateRepository repo = new InMemoryCertificateRepository();
        assertThrows(IllegalArgumentException.class, () -> repo.save(null));
    }

    @Test
    @Order(40)
    void testFindCertificateByStudentId() {
        InMemoryCertificateRepository repo = new InMemoryCertificateRepository();
        repo.save(certificate);
        List<Certificate> results = repo.findByStudentId("S001");
        assertEquals(1, results.size());
    }

    @Test
    @Order(41)
    void testFindCertificateByStudentIdNull() {
        InMemoryCertificateRepository repo = new InMemoryCertificateRepository();
        assertTrue(repo.findByStudentId(null).isEmpty());
    }

    @Test
    @Order(42)
    void testFindCertificateByCertificateNumber() {
        InMemoryCertificateRepository repo = new InMemoryCertificateRepository();
        repo.save(certificate);
        assertTrue(repo.findByCertificateNumber("BBA-2026-0001").isPresent());
    }

    @Test
    @Order(43)
    void testFindCertificateByCertificateNumberNotFound() {
        InMemoryCertificateRepository repo = new InMemoryCertificateRepository();
        assertFalse(repo.findByCertificateNumber("INVALID").isPresent());
    }

    @Test
    @Order(44)
    void testFindCertificateByCertificateNumberNull() {
        InMemoryCertificateRepository repo = new InMemoryCertificateRepository();
        assertFalse(repo.findByCertificateNumber(null).isPresent());
    }
}
