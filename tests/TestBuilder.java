package tests;

import creational_patterns.builder.CourseConfig;
import models.CourseCategory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestBuilder {

    // mandatory fields

    @Test
    void mandatoryFieldsAreSetCorrectly() {
        CourseConfig config = new CourseConfig.Builder(
                "CRS-001", "Classic Lash Extensions Course",
                CourseCategory.LASH, 3, 2500.00).build();
        assertEquals("CRS-001", config.getCourseId());
        assertEquals("Classic Lash Extensions Course", config.getTitle());
        assertEquals(CourseCategory.LASH, config.getCategory());
        assertEquals(3, config.getDurationDays());
        assertEquals(2500.00, config.getPrice(), 0.001);
    }

    // optional field defaults

    @Test
    void optionalFieldsHaveCorrectDefaults() {
        CourseConfig config = new CourseConfig.Builder(
                "CRS-002", "Gel Nail Course", CourseCategory.NAIL, 2, 1800.00).build();
        assertEquals("None", config.getPrerequisites());
        assertEquals(20, config.getMaxStudents());
        assertTrue(config.isActive());
        assertEquals("Bello Beauty Academy", config.getCertificationBody());
    }

    // optional fields overridden

    @Test
    void optionalFieldsCanBeOverridden() {
        CourseConfig config = new CourseConfig.Builder(
                "CRS-003", "Complete Lash Mastery", CourseCategory.LASH, 5, 5500.00)
                .description("Covers all lash techniques")
                .prerequisites("Basic beauty knowledge")
                .maxStudents(12)
                .certificationBody("SABT")
                .build();
        assertEquals("Covers all lash techniques", config.getDescription());
        assertEquals("Basic beauty knowledge", config.getPrerequisites());
        assertEquals(12, config.getMaxStudents());
        assertEquals("SABT", config.getCertificationBody());
    }

    @Test
    void inactiveFlagSetsActiveToFalse() {
        CourseConfig config = new CourseConfig.Builder(
                "CRS-004", "Old Course", CourseCategory.NAIL, 1, 0.0)
                .inactive()
                .build();
        assertFalse(config.isActive());
    }

    // invalid inputs

    @Test
    void blankCourseIdThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new CourseConfig.Builder("  ", "Title", CourseCategory.LASH, 1, 100.0));
    }

    @Test
    void negativePriceThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new CourseConfig.Builder("ID", "Title", CourseCategory.NAIL, 1, -100.0));
    }

    @Test
    void zeroDurationThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new CourseConfig.Builder("ID", "Title", CourseCategory.BROW, 0, 100.0));
    }

    @Test
    void invalidMaxStudentsThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new CourseConfig.Builder("ID", "Title", CourseCategory.MAKEUP, 1, 100.0)
                        .maxStudents(0));
    }
}