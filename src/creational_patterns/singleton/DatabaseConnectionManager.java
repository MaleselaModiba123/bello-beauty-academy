package creational_patterns.singleton;

import java.util.concurrent.atomic.AtomicInteger;

public final class DatabaseConnectionManager {

    // volatile makes sure only one instance is created even with multiple threads
    private static volatile DatabaseConnectionManager instance;

    private final String jdbcUrl;
    private final int maxPoolSize;
    private boolean connected;
    private final AtomicInteger activeConnections;

    private DatabaseConnectionManager() {
        this.jdbcUrl           = System.getenv().getOrDefault("DB_URL",
                "jdbc:postgresql://localhost:5432/bello_beauty");
        this.maxPoolSize       = 20;
        this.connected         = false;
        this.activeConnections = new AtomicInteger(0);
        connect();
    }

    // check once before locking, check again inside to avoid duplicate creation
    public static DatabaseConnectionManager getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnectionManager.class) {
                if (instance == null) {
                    instance = new DatabaseConnectionManager();
                }
            }
        }
        return instance;
    }

    private void connect() {
        System.out.println("[DB] Connecting to " + jdbcUrl);
        this.connected = true;
        System.out.println("[DB] Connected. Max pool size: " + maxPoolSize);
    }

    public synchronized String acquireConnection() {
        if (activeConnections.get() >= maxPoolSize)
            throw new IllegalStateException("Connection pool exhausted. Max: " + maxPoolSize);
        int connId = activeConnections.incrementAndGet();
        return "CONN-" + connId;
    }

    public synchronized void releaseConnection(String connectionId) {
        if (activeConnections.get() > 0)
            activeConnections.decrementAndGet();
        System.out.println("[DB] Released: " + connectionId);
    }

    public String  getJdbcUrl()           { return jdbcUrl; }
    public int     getMaxPoolSize()       { return maxPoolSize; }
    public boolean isConnected()          { return connected; }
    public int     getActiveConnections() { return activeConnections.get(); }

    // stops anyone from cloning the singleton to get a second instance
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("DatabaseConnectionManager is a Singleton.");
    }

    @Override
    public String toString() {
        return String.format("DatabaseConnectionManager[url=%s, active=%d/%d]",
                jdbcUrl, activeConnections.get(), maxPoolSize);
    }
}