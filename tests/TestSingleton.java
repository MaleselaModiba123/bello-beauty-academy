package tests;

import creational_patterns.singleton.DatabaseConnectionManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestSingleton {

    @Test
    void instanceIsNotNull() {
        assertNotNull(DatabaseConnectionManager.getInstance());
    }

    @Test
    void twoCallsReturnTheSameInstance() {
        DatabaseConnectionManager db1 = DatabaseConnectionManager.getInstance();
        DatabaseConnectionManager db2 = DatabaseConnectionManager.getInstance();
        assertSame(db1, db2);
    }

    @Test
    void multipleThreadsReturnTheSameInstance() throws InterruptedException {
        DatabaseConnectionManager[] refs = new DatabaseConnectionManager[5];
        Thread[] threads = new Thread[5];

        for (int i = 0; i < 5; i++) {
            final int idx = i;
            threads[i] = new Thread(() -> refs[idx] = DatabaseConnectionManager.getInstance());
            threads[i].start();
        }

        for (Thread t : threads) t.join();

        DatabaseConnectionManager expected = DatabaseConnectionManager.getInstance();
        for (DatabaseConnectionManager ref : refs) {
            assertSame(expected, ref);
        }
    }

    @Test
    void acquiringConnectionIncrementsCount() {
        DatabaseConnectionManager db = DatabaseConnectionManager.getInstance();
        int before = db.getActiveConnections();
        String conn = db.acquireConnection();
        assertEquals(before + 1, db.getActiveConnections());
        db.releaseConnection(conn);
    }

    @Test
    void releasingConnectionDecrementsCount() {
        DatabaseConnectionManager db = DatabaseConnectionManager.getInstance();
        String conn = db.acquireConnection();
        int before = db.getActiveConnections();
        db.releaseConnection(conn);
        assertEquals(before - 1, db.getActiveConnections());
    }

    @Test
    void cloningThrows() {
        DatabaseConnectionManager db = DatabaseConnectionManager.getInstance();
        assertThrows(CloneNotSupportedException.class, db::clone);
    }
}