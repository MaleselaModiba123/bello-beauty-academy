package creational_patterns.factory_method;

public class PaymentConfirmedNotificationCreator extends NotificationCreator {

    private final String courseName;

    public PaymentConfirmedNotificationCreator(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public Notification createNotification() {
        return new PaymentConfirmedNotification(courseName);
    }
}