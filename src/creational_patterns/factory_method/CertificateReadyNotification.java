package creational_patterns.factory_method;

public class CertificateReadyNotification implements Notification {

    private final String courseName;
    private final String certificateNumber;

    public CertificateReadyNotification(String courseName, String certificateNumber) {
        this.courseName        = courseName;
        this.certificateNumber = certificateNumber;
    }

    @Override
    public String getSubject() {
        return "Your Certificate is Ready — " + courseName;
    }

    @Override
    public String buildBody(String recipientName) {
        return String.format(
                "Dear %s,%n%nYour certificate for %s is ready. Certificate No: %s.%n%nBello Beauty Academy",
                recipientName, courseName, certificateNumber);
    }

    @Override
    public String getType() { return "CERTIFICATE_READY"; }
}