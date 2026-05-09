package repositories.inmemory;

import models.Course;
import models.CourseCategory;
import repositories.CourseRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

// In-memory implementation of CourseRepository using a HashMap
public class InMemoryCourseRepository implements CourseRepository {

    // HashMap stores courses with their courseId as the key
    private final Map<String, Course> storage = new HashMap<>();

    @Override
    public void save(Course course) {
        if (course == null) throw new IllegalArgumentException("Course must not be null.");
        storage.put(course.getCourseId(), course);
    }

    @Override
    public Optional<Course> findById(String id) {
        if (id == null) return Optional.empty();
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Course> findAll() {
        return Collections.unmodifiableList(
                storage.values().stream().collect(Collectors.toList())
        );
    }

    @Override
    public void delete(String id) {
        storage.remove(id);
    }

    @Override
    public List<Course> findActiveCourses() {
        return storage.values().stream()
                .filter(Course::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public List<Course> findByCategory(CourseCategory category) {
        if (category == null) return Collections.emptyList();
        return storage.values().stream()
                .filter(c -> c.getCategory() == category)
                .collect(Collectors.toList());
    }
}