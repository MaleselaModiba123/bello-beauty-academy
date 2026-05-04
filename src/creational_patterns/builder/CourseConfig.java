package creational_patterns.builder;

import models.CourseCategory;

public class CourseConfig {

    private final String courseId;
    private final String title;
    private final CourseCategory category;
    private final int durationDays;
    private final double price;
    private final String description;
    private final String prerequisites;
    private final int maxStudents;
    private final String certificationBody;
    private final boolean active;

    private CourseConfig(Builder builder) {
        this.courseId          = builder.courseId;
        this.title             = builder.title;
        this.category          = builder.category;
        this.durationDays      = builder.durationDays;
        this.price             = builder.price;
        this.description       = builder.description;
        this.prerequisites     = builder.prerequisites;
        this.maxStudents       = builder.maxStudents;
        this.certificationBody = builder.certificationBody;
        this.active            = builder.active;
    }

    public String         getCourseId()          { return courseId; }
    public String         getTitle()             { return title; }
    public CourseCategory getCategory()          { return category; }
    public int            getDurationDays()      { return durationDays; }
    public double         getPrice()             { return price; }
    public String         getDescription()       { return description; }
    public String         getPrerequisites()     { return prerequisites; }
    public int            getMaxStudents()       { return maxStudents; }
    public String         getCertificationBody() { return certificationBody; }
    public boolean        isActive()             { return active; }

    @Override
    public String toString() {
        return String.format(
                "CourseConfig{courseId=%s, title=%s, category=%s, duration=%d days, " +
                        "price=R%.2f, maxStudents=%d, active=%b}",
                courseId, title, category, durationDays, price, maxStudents, active);
    }

    public static class Builder {

        // mandatory
        private final String courseId;
        private final String title;
        private final CourseCategory category;
        private final int durationDays;
        private final double price;

        // optional — defaults set here
        private String  description       = "";
        private String  prerequisites     = "None";
        private int     maxStudents       = 20;
        private String  certificationBody = "Bello Beauty Academy";
        private boolean active            = true;

        public Builder(String courseId, String title, CourseCategory category,
                       int durationDays, double price) {
            if (courseId == null || courseId.isBlank())
                throw new IllegalArgumentException("courseId is required.");
            if (title == null || title.isBlank())
                throw new IllegalArgumentException("title is required.");
            if (category == null)
                throw new IllegalArgumentException("category is required.");
            if (durationDays <= 0)
                throw new IllegalArgumentException("durationDays must be positive.");
            if (price < 0)
                throw new IllegalArgumentException("price must not be negative.");
            this.courseId     = courseId;
            this.title        = title;
            this.category     = category;
            this.durationDays = durationDays;
            this.price        = price;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder prerequisites(String prerequisites) {
            this.prerequisites = prerequisites;
            return this;
        }

        public Builder maxStudents(int maxStudents) {
            if (maxStudents <= 0)
                throw new IllegalArgumentException("maxStudents must be positive.");
            this.maxStudents = maxStudents;
            return this;
        }

        public Builder certificationBody(String certificationBody) {
            this.certificationBody = certificationBody;
            return this;
        }

        public Builder inactive() {
            this.active = false;
            return this;
        }

        public CourseConfig build() {
            return new CourseConfig(this);
        }
    }
}