package repositories.inmemory;

import models.Enrollment;
import models.EnrollmentStatus;
import repositories.EnrollmentRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

// In-memory implementation of EnrollmentRepository using a HashMap
public class InMemoryEnrollmentRepository implements EnrollmentRepository {

    // HashMap stores enrollments with their enrollmentId as the key
    private final Map<String, Enrollment> storage = new HashMap<>();

    @Override
    public void save(Enrollment enrollment) {
        if (enrollment == null) throw new IllegalArgumentException("Enrollment must not be null.");
        storage.put(enrollment.getEnrollmentId(), enrollment);
    }

    @Override
    public Optional<Enrollment> findById(String id) {
        if (id == null) return Optional.empty();
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Enrollment> findAll() {
        return Collections.unmodifiableList(
                storage.values().stream().collect(Collectors.toList())
        );
    }

    @Override
    public void delete(String id) {
        storage.remove(id);
    }

    @Override
    public List<Enrollment> findByStudentId(String studentId) {
        if (studentId == null) return Collections.emptyList();
        return storage.values().stream()
                .filter(e -> e.getStudent().getUserId().equals(studentId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Enrollment> findByCourseId(String courseId) {
        if (courseId == null) return Collections.emptyList();
        return storage.values().stream()
                .filter(e -> e.getCourse().getCourseId().equals(courseId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Enrollment> findByStatus(EnrollmentStatus status) {
        if (status == null) return Collections.emptyList();
        return storage.values().stream()
                .filter(e -> e.getStatus() == status)
                .collect(Collectors.toList());
    }
}