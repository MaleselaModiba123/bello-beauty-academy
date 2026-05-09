package repositories.inmemory;

import models.Student;
import repositories.StudentRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

// In-memory implementation of StudentRepository using a HashMap
public class InMemoryStudentRepository implements StudentRepository {

    // HashMap stores students with their userId as the key
    private final Map<String, Student> storage = new HashMap<>();

    @Override
    public void save(Student student) {
        if (student == null) throw new IllegalArgumentException("Student must not be null.");
        storage.put(student.getUserId(), student);
    }

    @Override
    public Optional<Student> findById(String id) {
        if (id == null) return Optional.empty();
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Student> findAll() {
        return Collections.unmodifiableList(
                storage.values().stream().collect(Collectors.toList())
        );
    }

    @Override
    public void delete(String id) {
        // silently ignored if the ID does not exist
        storage.remove(id);
    }

    @Override
    public Optional<Student> findByEmail(String email) {
        if (email == null) return Optional.empty();
        return storage.values().stream()
                .filter(s -> s.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    @Override
    public List<Student> findActiveStudents() {
        return storage.values().stream()
                .filter(s -> s.getActiveEnrollmentCount() > 0)
                .collect(Collectors.toList());
    }
}