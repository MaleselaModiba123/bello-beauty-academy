package api;

import factories.RepositoryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import repositories.CourseRepository;
import repositories.EnrollmentRepository;
import repositories.StudentRepository;

// registers in-memory repository implementations as Spring beans
// this allows Spring to inject them into the service layer automatically
@Configuration
public class AppConfig {

    @Bean
    public StudentRepository studentRepository() {
        return RepositoryFactory.getStudentRepository("MEMORY");
    }

    @Bean
    public CourseRepository courseRepository() {
        return RepositoryFactory.getCourseRepository("MEMORY");
    }

    @Bean
    public EnrollmentRepository enrollmentRepository() {
        return RepositoryFactory.getEnrollmentRepository("MEMORY");
    }
}