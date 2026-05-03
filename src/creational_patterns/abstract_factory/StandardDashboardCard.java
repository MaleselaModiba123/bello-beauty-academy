package creational_patterns.abstract_factory;

public class StandardDashboardCard implements DashboardCard {
    @Override
    public String render(String title, String value) {
        return String.format("[STANDARD] %s: %s", title, value);
    }
}