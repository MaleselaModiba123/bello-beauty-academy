package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Course {

    private final String courseId;
    private String title;
    private String description;
    private CourseCategory category;
    private int durationDays;
    private double price;
    private boolean active;
    private final List<String> materials;

    public Course(String courseId, String title, String description,
                  CourseCategory category, int durationDays, double price) {
        if (courseId == null || courseId.isBlank())
            throw new IllegalArgumentException("courseId must not be blank.");
        if (durationDays <= 0)
            throw new IllegalArgumentException("durationDays must be positive.");
        if (price < 0)
            throw new IllegalArgumentException("price must not be negative.");
        this.courseId     = courseId;
        this.title        = title;
        this.description  = description;
        this.category     = category;
        this.durationDays = durationDays;
        this.price        = price;
        this.active       = true;
        this.materials    = new ArrayList<>();
    }

    public void addMaterial(String materialPath) {
        if (materialPath != null && !materialPath.isBlank()) {
            materials.add(materialPath);
        }
    }

    public List<String> getMaterials() {
        return Collections.unmodifiableList(materials);
    }

    public String         getCourseId()     { return courseId; }
    public String         getTitle()        { return title; }
    public String         getDescription()  { return description; }
    public CourseCategory getCategory()     { return category; }
    public int            getDurationDays() { return durationDays; }
    public double         getPrice()        { return price; }
    public boolean        isActive()        { return active; }

    public void setTitle(String title)               { this.title = title; }
    public void setDescription(String description)   { this.description = description; }
    public void setCategory(CourseCategory category) { this.category = category; }
    public void setDurationDays(int days)            { this.durationDays = days; }
    public void setPrice(double price)               { this.price = price; }
    public void setActive(boolean active)            { this.active = active; }

    @Override
    public String toString() {
        return String.format("Course[id=%s, title=%s, category=%s, price=%.2f, active=%b]",
                courseId, title, category, price, active);
    }
}