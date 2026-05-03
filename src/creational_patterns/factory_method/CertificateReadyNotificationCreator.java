package creational_patterns.factory_method;

public class CertificateReadyNotificationCreator extends NotificationCreator {

    private final String courseName;
    private final String certificateNumber;

    public CertificateReadyNotificationCreator(String courseName, String certificateNumber) {
        this.courseName        = courseName;
        this.certificateNumber = certificateNumber;
    }

    @Override
    public Notification createNotification() {
        return new CertificateReadyNotification(courseName, certificateNumber);
    }
}