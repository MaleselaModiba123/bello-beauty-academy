package models;

import java.time.LocalDateTime;

public class Enrollment {

    private final String enrollmentId;
    private final Student student;
    private final Course course;
    private EnrollmentStatus status;
    private final LocalDateTime enrolledAt;
    private LocalDateTime activatedAt;
    private LocalDateTime completedAt;
    private LocalDateTime cancelledAt;

    public Enrollment(String enrollmentId, Student student, Course course) {
        if (enrollmentId == null || enrollmentId.isBlank())
            throw new IllegalArgumentException("enrollmentId must not be blank.");
        if (student == null) throw new IllegalArgumentException("Student must not be null.");
        if (course == null)  throw new IllegalArgumentException("Course must not be null.");
        if (!course.isActive())
            throw new IllegalStateException("Cannot enroll in an inactive course.");
        this.enrollmentId = enrollmentId;
        this.student      = student;
        this.course       = course;
        this.status       = EnrollmentStatus.PENDING;
        this.enrolledAt   = LocalDateTime.now();
    }

    // enforces valid state transition: PENDING → ACTIVE
    public void activate() {
        if (status != EnrollmentStatus.PENDING)
            throw new IllegalStateException("Only PENDING enrollments can be activated.");
        this.status      = EnrollmentStatus.ACTIVE;
        this.activatedAt = LocalDateTime.now();
    }

    // enforces valid state transition: ACTIVE → COMPLETED
    public void complete() {
        if (status != EnrollmentStatus.ACTIVE)
            throw new IllegalStateException("Only ACTIVE enrollments can be completed.");
        this.status      = EnrollmentStatus.COMPLETED;
        this.completedAt = LocalDateTime.now();
    }

    // enforces valid state transition: PENDING or ACTIVE → CANCELLED
    public void cancel() {
        if (status == EnrollmentStatus.COMPLETED)
            throw new IllegalStateException("Cannot cancel a completed enrollment.");
        this.status      = EnrollmentStatus.CANCELLED;
        this.cancelledAt = LocalDateTime.now();
    }

    public String           getEnrollmentId() { return enrollmentId; }
    public Student          getStudent()      { return student; }
    public Course           getCourse()       { return course; }
    public EnrollmentStatus getStatus()       { return status; }
    public LocalDateTime    getEnrolledAt()   { return enrolledAt; }
    public LocalDateTime    getActivatedAt()  { return activatedAt; }
    public LocalDateTime    getCompletedAt()  { return completedAt; }
    public LocalDateTime    getCancelledAt()  { return cancelledAt; }

    @Override
    public String toString() {
        return String.format("Enrollment[id=%s, student=%s, course=%s, status=%s]",
                enrollmentId, student.getName(), course.getTitle(), status);
    }
}