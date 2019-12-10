package pl.example.spring.punkty;

import io.vavr.collection.List;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.example.spring.punkty.db.StudentRepository;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest {
    @Autowired
    private StudentRepository repository;
    @Test
    public void getEmptyList() {
        final StudentService service = new StudentService(repository);
        List<Student> students = service.getStudents();
        assertTrue(students.isEmpty());
    }

    @After
    public void cleanAfterTest() {this.repository.deleteAll();}
    @Test
    public void addStudent() {
        final StudentService service = new StudentService(repository);
        final Student created = service.addStudent(new NewStudent("Student1", "1-2-3", "IP"));
        assertNotNull(created);
    }

    @Test
    public void addStudentIsReturned() {
        final StudentService service = new StudentService(repository);
        final Student created = service.addStudent(new NewStudent("Student1", "1-2-3", "IP"));
        final List<Student> all = service.getStudents();
        //assertEquals("Student1",all.get(0).name);
        assertEquals("Student1", all.head().name);
    }

    @Test
    public void addStudentHasNewId() {
        final StudentService service = new StudentService(repository);
        final Student created1 = service.addStudent(new NewStudent("Student1", "1-2-3", "IP"));
        final Student created2 = service.addStudent(new NewStudent("Student2", "1-2-4", "IP"));
        assertNotEquals(created1.id,created2.id);
        assertEquals(2,service.getStudents().size());
    }
}