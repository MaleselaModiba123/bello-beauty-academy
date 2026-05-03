package creational_patterns.abstract_factory;

public class AccessibleDashboardCard implements DashboardCard {
    @Override
    public String render(String title, String value) {
        return String.format("[ ACCESSIBLE ] %s: %s", title.toUpperCase(), value);
    }
}