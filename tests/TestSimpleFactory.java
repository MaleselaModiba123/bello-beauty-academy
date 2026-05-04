package tests;

import creational_patterns.simple_factory.UserFactory;
import models.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestSimpleFactory {

    // correct types returned

    @Test
    void createStudentReturnsStudentInstance() {
        User user = UserFactory.createUser("student", "STU-001", "Amira Patel",
                "amira@example.com", "hash123", "+27821234567");
        assertInstanceOf(Student.class, user);
    }

    @Test
    void createTrainerReturnsTrainerInstance() {
        User user = UserFactory.createUser("trainer", "TRN-001", "Leila Hendricks",
                "leila@example.com", "hash123", "Lash specialist");
        assertInstanceOf(Trainer.class, user);
    }

    @Test
    void createAdminReturnsAdministratorInstance() {
        User user = UserFactory.createUser("administrator", "ADM-001", "Fatima Essop",
                "fatima@example.com", "hash123", null);
        assertInstanceOf(Administrator.class, user);
    }

    // attributes set correctly

    @Test
    void createdUserHasCorrectNameAndEmail() {
        User user = UserFactory.createUser("student", "STU-001", "Amira Patel",
                "amira@example.com", "hash123", "+27821234567");
        assertEquals("Amira Patel", user.getName());
        assertEquals("amira@example.com", user.getEmail());
    }

    @Test
    void createdUserIsActiveByDefault() {
        User user = UserFactory.createUser("student", "STU-001", "Amira Patel",
                "amira@example.com", "hash123", "+27821234567");
        assertTrue(user.isActive());
    }

    @Test
    void createdTrainerHasCorrectRole() {
        User user = UserFactory.createUser("trainer", "TRN-001", "Leila Hendricks",
                "leila@example.com", "hash123", "Bio text");
        assertEquals(UserRole.TRAINER, user.getRole());
    }

    // edge cases

    @Test
    void roleMatchingIsCaseInsensitive() {
        assertDoesNotThrow(() -> UserFactory.createUser("STUDENT", "STU-001",
                "Amira Patel", "amira@example.com", "hash123", "+27821234567"));
    }

    @Test
    void unknownRoleThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> UserFactory.createUser("receptionist", "X-001",
                        "Test", "test@x.com", "hash123", null));
    }

    @Test
    void nullRoleThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> UserFactory.createUser(null, "X-001",
                        "Test", "test@x.com", "hash123", null));
    }
}