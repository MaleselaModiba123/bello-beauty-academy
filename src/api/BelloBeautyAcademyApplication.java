
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"api", "services", "repositories", "exceptions", "config"})public class BelloBeautyAcademyApplication {
    public static void main(String[] args) {
        SpringApplication.run(BelloBeautyAcademyApplication.class, args);
    }
}