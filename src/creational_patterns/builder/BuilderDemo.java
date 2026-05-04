package creational_patterns.builder;

import models.CourseCategory;

public class BuilderDemo {

    public static void main(String[] args) {

        // full configuration
        CourseConfig lashMastery = new CourseConfig.Builder(
                "CRS-001", "Complete Lash Mastery Course", CourseCategory.LASH, 5, 5500.00)
                .description("Covers classic, volume, hybrid, and mega volume techniques.")
                .prerequisites("None — suitable for beginners")
                .maxStudents(12)
                .certificationBody("South African Beauty Federation")
                .build();

        System.out.println(lashMastery);

        // minimal configuration — only mandatory fields
        CourseConfig gelNails = new CourseConfig.Builder(
                "CRS-002", "Gel Nail Application Course", CourseCategory.NAIL, 2, 1800.00)
                .build();

        System.out.println(gelNails);
    }
}