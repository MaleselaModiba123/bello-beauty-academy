package creational_patterns.abstract_factory;

public class AccessibleUIFactory implements UIComponentFactory {
    @Override
    public DashboardCard createDashboardCard() { return new AccessibleDashboardCard(); }
    @Override
    public CourseCard    createCourseCard()    { return new AccessibleCourseCard(); }
}