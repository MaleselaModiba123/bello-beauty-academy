package services;

import exceptions.CourseNotFoundException;
import models.Course;
import models.CourseCategory;
import repositories.CourseRepository;

import java.util.List;

// handles all course-related business logic
// uses CourseRepository for persistence — no direct storage here
public class CourseService {

    private final CourseRepository courseRepository;

    // constructor injection
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // create a new course — rejects duplicate courseId
    public Course createCourse(Course course) {
        if (course == null) throw new IllegalArgumentException("Course must not be null.");
        if (courseRepository.findById(course.getCourseId()).isPresent()) {
            throw new IllegalArgumentException("A course with ID " + course.getCourseId() + " already exists.");
        }
        courseRepository.save(course);
        return course;
    }

    // find a course by ID — throws if not found
    public Course getCourseById(String courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(courseId));
    }

    // return all courses
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // return only active courses
    public List<Course> getActiveCourses() {
        return courseRepository.findActiveCourses();
    }

    // return courses by category
    public List<Course> getCoursesByCategory(CourseCategory category) {
        if (category == null) throw new IllegalArgumentException("Category must not be null.");
        return courseRepository.findByCategory(category);
    }

    // update an existing course — throws if not found
    public Course updateCourse(String courseId, Course updatedCourse) {
        if (updatedCourse == null) throw new IllegalArgumentException("Updated course must not be null.");
        getCourseById(courseId);
        courseRepository.save(updatedCourse);
        return updatedCourse;
    }

    // deactivate a course — throws if already inactive
    public void deactivateCourse(String courseId) {
        Course course = getCourseById(courseId);
        if (!course.isActive()) {
            throw new IllegalStateException("Course " + courseId + " is already inactive.");
        }
        course.deactivate();
        courseRepository.save(course);
    }
}