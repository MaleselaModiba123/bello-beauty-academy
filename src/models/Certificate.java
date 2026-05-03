package models;

import java.time.LocalDate;

public class Certificate {

    private final String certificateId;
    private final Student student;
    private final Course course;
    private final LocalDate issueDate;
    private final String certificateNumber;
    private final String pdfStoragePath;

    public Certificate(String certificateId, Student student, Course course,
                       String certificateNumber, String pdfStoragePath) {
        if (certificateId == null || certificateId.isBlank())
            throw new IllegalArgumentException("certificateId must not be blank.");
        if (student == null) throw new IllegalArgumentException("Student must not be null.");
        if (course == null)  throw new IllegalArgumentException("Course must not be null.");
        this.certificateId     = certificateId;
        this.student           = student;
        this.course            = course;
        this.issueDate         = LocalDate.now();
        this.certificateNumber = certificateNumber;
        this.pdfStoragePath    = pdfStoragePath;
    }

    // no setters — once issued a certificate must never be modified
    public String    getCertificateId()     { return certificateId; }
    public Student   getStudent()           { return student; }
    public Course    getCourse()            { return course; }
    public LocalDate getIssueDate()         { return issueDate; }
    public String    getCertificateNumber() { return certificateNumber; }
    public String    getPdfStoragePath()    { return pdfStoragePath; }

    @Override
    public String toString() {
        return String.format("Certificate[id=%s, student=%s, course=%s, issued=%s, certNo=%s]",
                certificateId, student.getName(), course.getTitle(),
                issueDate, certificateNumber);
    }
}