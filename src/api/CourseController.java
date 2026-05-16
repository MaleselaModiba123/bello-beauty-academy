package api;

import exceptions.CourseNotFoundException;
import models.Course;
import models.CourseCategory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.CourseService;

import java.util.List;

// thin controller — all business logic stays in CourseService
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    // constructor injection
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // GET /api/courses — return all courses
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    // GET /api/courses/active — return only active courses
    @GetMapping("/active")
    public ResponseEntity<List<Course>> getActiveCourses() {
        return ResponseEntity.ok(courseService.getActiveCourses());
    }

    // GET /api/courses/{id} — return a course by ID
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable String id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    // GET /api/courses/category/{category} — return courses by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Course>> getCoursesByCategory(@PathVariable String category) {
        CourseCategory cat = CourseCategory.valueOf(category.toUpperCase());
        return ResponseEntity.ok(courseService.getCoursesByCategory(cat));
    }

    // POST /api/courses — create a new course
    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course created = courseService.createCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT /api/courses/{id} — update an existing course
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable String id,
                                               @RequestBody Course course) {
        return ResponseEntity.ok(courseService.updateCourse(id, course));
    }

    // DELETE /api/courses/{id} — deactivate a course
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivateCourse(@PathVariable String id) {
        courseService.deactivateCourse(id);
        return ResponseEntity.noContent().build();
    }

    // handle CourseNotFoundException — returns 404
    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<String> handleNotFound(CourseNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // handle IllegalArgumentException — returns 400
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBadRequest(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // handle IllegalStateException — returns 409
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleConflict(IllegalStateException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}