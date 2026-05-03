package creational_patterns.abstract_factory;

// abstract products

interface DashboardCard {
    String render(String title, String value);
}

interface CourseCard {
    String render(String courseTitle, String category, double price);
}

// standard theme products

class StandardDashboardCard implements DashboardCard {
    @Override
    public String render(String title, String value) {
        return String.format("[STANDARD] %s: %s", title, value);
    }
}

class StandardCourseCard implements CourseCard {
    @Override
    public String render(String courseTitle, String category, double price) {
        return String.format("[STANDARD] %s (%s) — R%.2f", courseTitle, category, price);
    }
}

// accessible theme products (high contrast)

class AccessibleDashboardCard implements DashboardCard {
    @Override
    public String render(String title, String value) {
        return String.format("[ ACCESSIBLE ] %s: %s", title.toUpperCase(), value);
    }
}

class AccessibleCourseCard implements CourseCard {
    @Override
    public String render(String courseTitle, String category, double price) {
        return String.format("[ ACCESSIBLE ] %s | %s | R%.2f",
                courseTitle, category.toUpperCase(), price);
    }
}

// abstract factory interface

interface UIComponentFactory {
    DashboardCard createDashboardCard();
    CourseCard    createCourseCard();
}

// concrete factories

class StandardUIFactory implements UIComponentFactory {
    @Override
    public DashboardCard createDashboardCard() { return new StandardDashboardCard(); }
    @Override
    public CourseCard    createCourseCard()    { return new StandardCourseCard(); }
}

class AccessibleUIFactory implements UIComponentFactory {
    @Override
    public DashboardCard createDashboardCard() { return new AccessibleDashboardCard(); }
    @Override
    public CourseCard    createCourseCard()    { return new AccessibleCourseCard(); }
}

// client — only knows the abstract factory, never the concrete types

class AcademyDashboard {

    private final DashboardCard dashboardCard;
    private final CourseCard courseCard;

    AcademyDashboard(UIComponentFactory factory) {
        this.dashboardCard = factory.createDashboardCard();
        this.courseCard    = factory.createCourseCard();
    }

    void renderStudentDashboard(String studentName, int enrollments,
                                String courseTitle, String category, double price) {
        System.out.println("Dashboard for " + studentName);
        System.out.println(dashboardCard.render("Active Enrollments", String.valueOf(enrollments)));
        System.out.println(courseCard.render(courseTitle, category, price));
    }
}