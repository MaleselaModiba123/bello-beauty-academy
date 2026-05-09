package repositories;

import models.Course;
import models.CourseCategory;
import java.util.List;

public interface CourseRepository extends Repository<Course, String> {

    // Return only courses that are currently active
    List<Course> findActiveCourses();

    // Filter courses by category (Lash, Brow, Nail, Makeup)
    List<Course> findByCategory(CourseCategory category);
}