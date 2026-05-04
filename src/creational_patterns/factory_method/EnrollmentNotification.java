package creational_patterns.factory_method;

public class EnrollmentNotification implements Notification {

    private final String courseName;

    public EnrollmentNotification(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String getSubject() {
        return "Enrollment Received — " + courseName;
    }

    @Override
    public String buildBody(String recipientName) {
        return String.format(
                "Dear %s,%n%nYour enrollment in %s has been received. " +
                        "Please upload your proof of payment to activate your enrollment.%n%nBello Beauty Academy",
                recipientName, courseName);
    }

    @Override
    public String getType() { return "ENROLLMENT"; }
}