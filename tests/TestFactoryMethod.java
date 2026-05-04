package tests;

import creational_patterns.factory_method.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestFactoryMethod {

    // correct types returned

    @Test
    void enrollmentCreatorProducesEnrollmentNotification() {
        NotificationCreator creator = new EnrollmentNotificationCreator("Classic Lash Extensions Course");
        Notification n = creator.createNotification();
        assertEquals("ENROLLMENT", n.getType());
    }

    @Test
    void paymentCreatorProducesPaymentConfirmedNotification() {
        NotificationCreator creator = new PaymentConfirmedNotificationCreator("Classic Lash Extensions Course");
        Notification n = creator.createNotification();
        assertEquals("PAYMENT_CONFIRMED", n.getType());
    }

    @Test
    void certificateCreatorProducesCertificateReadyNotification() {
        NotificationCreator creator = new CertificateReadyNotificationCreator(
                "Classic Lash Extensions Course", "BBA-LASH-2026-00142");
        Notification n = creator.createNotification();
        assertEquals("CERTIFICATE_READY", n.getType());
    }

    // subject and body content

    @Test
    void enrollmentSubjectContainsCourseName() {
        Notification n = new EnrollmentNotificationCreator("Classic Lash Extensions Course")
                .createNotification();
        assertTrue(n.getSubject().contains("Classic Lash Extensions Course"));
    }

    @Test
    void enrollmentBodyContainsRecipientName() {
        Notification n = new EnrollmentNotificationCreator("Classic Lash Extensions Course")
                .createNotification();
        assertTrue(n.buildBody("Amira Patel").contains("Amira Patel"));
    }

    @Test
    void certificateBodyContainsCertificateNumber() {
        Notification n = new CertificateReadyNotificationCreator(
                "Classic Lash Extensions Course", "BBA-LASH-2026-00142")
                .createNotification();
        assertTrue(n.buildBody("Amira Patel").contains("BBA-LASH-2026-00142"));
    }
}