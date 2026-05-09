package repositories.filesystem;

import models.Course;
import models.CourseCategory;
import repositories.CourseRepository;

import java.util.List;
import java.util.Optional;

// Stub for a future filesystem-backed implementation of CourseRepository
// Full implementation would serialize Course objects to a JSON file on disk
public class FileSystemCourseRepository implements CourseRepository {

    // Would read from and write to a JSON file at a configurable file path
    @Override
    public void save(Course course) {
        throw new UnsupportedOperationException("Filesystem storage not yet implemented.");
    }

    @Override
    public Optional<Course> findById(String id) {
        throw new UnsupportedOperationException("Filesystem storage not yet implemented.");
    }

    @Override
    public List<Course> findAll() {
        throw new UnsupportedOperationException("Filesystem storage not yet implemented.");
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Filesystem storage not yet implemented.");
    }

    @Override
    public List<Course> findActiveCourses() {
        throw new UnsupportedOperationException("Filesystem storage not yet implemented.");
    }

    @Override
    public List<Course> findByCategory(CourseCategory category) {
        throw new UnsupportedOperationException("Filesystem storage not yet implemented.");
    }
}