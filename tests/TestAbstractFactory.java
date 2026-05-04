package tests;

import creational_patterns.abstract_factory.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestAbstractFactory {

    @Test
    void standardFactoryCreatesDashboardCard() {
        UIComponentFactory factory = new StandardUIFactory();
        assertNotNull(factory.createDashboardCard());
    }

    @Test
    void standardFactoryCreatesCourseCard() {
        UIComponentFactory factory = new StandardUIFactory();
        assertNotNull(factory.createCourseCard());
    }

    @Test
    void accessibleFactoryCreatesDashboardCard() {
        UIComponentFactory factory = new AccessibleUIFactory();
        assertNotNull(factory.createDashboardCard());
    }

    @Test
    void accessibleFactoryCreatesCourseCard() {
        UIComponentFactory factory = new AccessibleUIFactory();
        assertNotNull(factory.createCourseCard());
    }

    @Test
    void standardDashboardCardRenderContainsTitleAndValue() {
        DashboardCard card = new StandardUIFactory().createDashboardCard();
        String output = card.render("Active Enrollments", "3");
        assertTrue(output.contains("Active Enrollments"));
        assertTrue(output.contains("3"));
    }

    @Test
    void accessibleRenderDiffersFromStandard() {
        DashboardCard standard   = new StandardUIFactory().createDashboardCard();
        DashboardCard accessible = new AccessibleUIFactory().createDashboardCard();
        assertNotEquals(standard.render("Title", "Value"),
                accessible.render("Title", "Value"));
    }

    @Test
    void standardCourseCardRenderContainsPriceAndTitle() {
        CourseCard card = new StandardUIFactory().createCourseCard();
        String output  = card.render("Classic Lash Extensions Course", "Lash", 2500.00);
        assertTrue(output.contains("Classic Lash Extensions Course"));
        assertTrue(output.contains("2500"));
    }

    @Test
    void accessibleCourseCardRendersUppercaseCategory() {
        CourseCard card = new AccessibleUIFactory().createCourseCard();
        String output  = card.render("Gel Nail Application", "Nail", 1800.00);
        assertTrue(output.contains("NAIL"));
    }

    @Test
    void dashboardClientWorksWithStandardFactory() {
        assertDoesNotThrow(() -> {
            AcademyDashboard dashboard = new AcademyDashboard(new StandardUIFactory());
            dashboard.renderStudentDashboard("Amira Patel", 2,
                    "Classic Lash Extensions Course", "Lash", 2500.00);
        });
    }

    @Test
    void dashboardClientWorksWithAccessibleFactory() {
        assertDoesNotThrow(() -> {
            AcademyDashboard dashboard = new AcademyDashboard(new AccessibleUIFactory());
            dashboard.renderStudentDashboard("Thabo Mokoena", 1,
                    "Gel Nail Application Course", "Nail", 1800.00);
        });
    }
}