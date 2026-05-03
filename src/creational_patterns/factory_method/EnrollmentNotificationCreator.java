package creational_patterns.factory_method;

public class EnrollmentNotificationCreator extends NotificationCreator {

    private final String courseName;

    public EnrollmentNotificationCreator(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public Notification createNotification() {
        return new EnrollmentNotification(courseName);
    }
}