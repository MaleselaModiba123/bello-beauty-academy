package creational_patterns.abstract_factory;

public class AccessibleCourseCard implements CourseCard {
    @Override
    public String render(String courseTitle, String category, double price) {
        return String.format("[ ACCESSIBLE ] %s | %s | R%.2f",
                courseTitle, category.toUpperCase(), price);
    }
}