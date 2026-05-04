package creational_patterns.abstract_factory;

public class StandardUIFactory implements UIComponentFactory {
    @Override
    public DashboardCard createDashboardCard() { return new StandardDashboardCard(); }
    @Override
    public CourseCard    createCourseCard()    { return new StandardCourseCard(); }
}