package creational_patterns.factory_method;

public interface Notification {
    String getSubject();
    String buildBody(String recipientName);
    String getType();
}