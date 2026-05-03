package creational_patterns.factory_method;

public abstract class NotificationCreator {

    // subclasses override this to decide which Notification to instantiate
    public abstract Notification createNotification();

    public void send(String recipientEmail, String recipientName) {
        Notification notification = createNotification();
        System.out.printf("[%s] To: %s <%s>%n", notification.getType(), recipientName, recipientEmail);
        System.out.println("Subject : " + notification.getSubject());
        System.out.println(notification.buildBody(recipientName));
    }
}