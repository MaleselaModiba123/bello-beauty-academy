package creational_patterns.factory_method;

public class PaymentConfirmedNotification implements Notification {

    private final String courseName;

    public PaymentConfirmedNotification(String courseName) {
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