package creational_patterns.factory_method;

// concrete products

class EnrollmentNotification implements Notification {

    private final String courseName;

    EnrollmentNotification(String courseName) {
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

class PaymentConfirmedNotification implements Notification {

    private final String courseName;

    PaymentConfirmedNotification(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String getSubject() {
        return "Payment Confirmed — Your Enrollment is Now Active";
    }

    @Override
    public String buildBody(String recipientName) {
        return String.format(
                "Dear %s,%n%nYour payment for %s has been confirmed. " +
                        "Your enrollment is now ACTIVE.%n%nBello Beauty Academy",
                recipientName, courseName);
    }

    @Override
    public String getType() { return "PAYMENT_CONFIRMED"; }
}

class CertificateReadyNotification implements Notification {

    private final String courseName;
    private final String certificateNumber;

    CertificateReadyNotification(String courseName, String certificateNumber) {
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

// abstract creator

abstract class NotificationCreator {

    // subclasses override this to decide which Notification to instantiate
    public abstract Notification createNotification();

    public void send(String recipientEmail, String recipientName) {
        Notification notification = createNotification();
        System.out.printf("[%s] To: %s <%s>%n", notification.getType(), recipientName, recipientEmail);
        System.out.println("Subject : " + notification.getSubject());
        System.out.println(notification.buildBody(recipientName));
    }
}

// concrete creators

class EnrollmentNotificationCreator extends NotificationCreator {
    private final String courseName;
    EnrollmentNotificationCreator(String courseName) { this.courseName = courseName; }

    @Override
    public Notification createNotification() {
        return new EnrollmentNotification(courseName);
    }
}

class PaymentConfirmedNotificationCreator extends NotificationCreator {
    private final String courseName;
    PaymentConfirmedNotificationCreator(String courseName) { this.courseName = courseName; }

    @Override
    public Notification createNotification() {
        return new PaymentConfirmedNotification(courseName);
    }
}

class CertificateReadyNotificationCreator extends NotificationCreator {
    private final String courseName;
    private final String certificateNumber;
    CertificateReadyNotificationCreator(String courseName, String certNo) {
        this.courseName        = courseName;
        this.certificateNumber = certNo;
    }

    @Override
    public Notification createNotification() {
        return new CertificateReadyNotification(courseName, certificateNumber);
    }
}