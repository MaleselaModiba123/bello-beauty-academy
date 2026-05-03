package creational_patterns.abstract_factory;

public class StandardCourseCard implements CourseCard {
    @Override
    public String render(String courseTitle, String category, double price) {
        return String.format("[STANDARD] %s (%s) — R%.2f", courseTitle, category, price);
    }
}