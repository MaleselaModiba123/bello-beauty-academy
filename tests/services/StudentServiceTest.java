package services;

import exceptions.StudentNotFoundException;
import models.Student;
import models.UserRole;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import repositories.StudentRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentServiceTest {

    private StudentRepository studentRepository;
    private StudentService studentService;
    private Student student;

    @BeforeEach
    void setup() {
        // mock the repository so tests run without real storage
        studentRepository = Mockito.mock(StudentRepository.class);
        studentService = new StudentService(studentRepository);
        student = new Student("S001", "Aaliyah Adams", "aaliyah@email.com", "hashed123", "0821234567");
    }

    // register a valid student successfully
    @Test
    @Order(1)
    void testRegisterStudentSuccess() {
        when(studentRepository.findByEmail("aaliyah@email.com")).thenReturn(Optional.empty());
        Student result = studentService.registerStudent(student);
        assertEquals("S001", result.getUserId());
        verify(studentRepository, times(1)).save(student);
    }

    // duplicate email should throw IllegalArgumentException
    @Test
    @Order(2)
    void testRegisterStudentDuplicateEmailThrows() {
        when(studentRepository.findByEmail("aaliyah@email.com")).thenReturn(Optional.of(student));
        assertThrows(IllegalArgumentException.class, () -> studentService.registerStudent(student));
        verify(studentRepository, never()).save(any());
    }

    // null student should throw IllegalArgumentException
    @Test
    @Order(3)
    void testRegisterNullStudentThrows() {
        assertThrows(IllegalArgumentException.class, () -> studentService.registerStudent(null));
    }

    // find a student that exists
    @Test
    @Order(4)
    void testGetStudentByIdSuccess() {
        when(studentRepository.findById("S001")).thenReturn(Optional.of(student));
        Student result = studentService.getStudentById("S001");
        assertEquals("Aaliyah Adams", result.getName());
    }

    // find a student that does not exist — should throw StudentNotFoundException
    @Test
    @Order(5)
    void testGetStudentByIdNotFound() {
        when(studentRepository.findById("UNKNOWN")).thenReturn(Optional.empty());
        assertThrows(StudentNotFoundException.class, () -> studentService.getStudentById("UNKNOWN"));
    }

    // get all students returns the full list
    @Test
    @Order(6)
    void testGetAllStudents() {
        when(studentRepository.findAll()).thenReturn(List.of(student));
        List<Student> results = studentService.getAllStudents();
        assertEquals(1, results.size());
    }

    // update a student that exists
    @Test
    @Order(7)
    void testUpdateStudentSuccess() {
        when(studentRepository.findById("S001")).thenReturn(Optional.of(student));
        Student updated = new Student("S001", "Aaliyah Updated", "aaliyah@email.com", "hashed123", "0821234567");
        Student result = studentService.updateStudent("S001", updated);
        assertEquals("Aaliyah Updated", result.getName());
        verify(studentRepository, times(1)).save(updated);
    }

    // update a student that does not exist — should throw StudentNotFoundException
    @Test
    @Order(8)
    void testUpdateStudentNotFound() {
        when(studentRepository.findById("UNKNOWN")).thenReturn(Optional.empty());
        assertThrows(StudentNotFoundException.class,
                () -> studentService.updateStudent("UNKNOWN", student));
    }

    // update with null student should throw IllegalArgumentException
    @Test
    @Order(9)
    void testUpdateNullStudentThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> studentService.updateStudent("S001", null));
    }

    // delete a student that exists
    @Test
    @Order(10)
    void testDeleteStudentSuccess() {
        when(studentRepository.findById("S001")).thenReturn(Optional.of(student));
        studentService.deleteStudent("S001");
        verify(studentRepository, times(1)).delete("S001");
    }

    // delete a student that does not exist — should throw StudentNotFoundException
    @Test
    @Order(11)
    void testDeleteStudentNotFound() {
        when(studentRepository.findById("UNKNOWN")).thenReturn(Optional.empty());
        assertThrows(StudentNotFoundException.class, () -> studentService.deleteStudent("UNKNOWN"));
    }
}