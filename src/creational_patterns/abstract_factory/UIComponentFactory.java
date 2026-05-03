package creational_patterns.abstract_factory;

public interface UIComponentFactory {
    DashboardCard createDashboardCard();
    CourseCard    createCourseCard();
}