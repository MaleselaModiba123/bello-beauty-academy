package creational_patterns.abstract_factory;

// client — only knows UIComponentFactory, never the concrete types
public class AcademyDashboard {

    private final DashboardCard dashboardCard;
    private final CourseCard courseCard;

    public AcademyDashboard(UIComponentFactory factory) {
        this.dashboardCard = factory.createDashboardCard();
        this.courseCard    = factory.createCourseCard();
    }

    public void renderStudentDashboard(String studentName, int enrollments,
                                       String courseTitle, String category, double price) {
        System.out.println("Dashboard for " + studentName);
        System.out.println(dashboardCard.render("Active Enrollments", String.valueOf(enrollments)));
        System.out.println(courseCard.render(courseTitle, category, price));
    }
}
