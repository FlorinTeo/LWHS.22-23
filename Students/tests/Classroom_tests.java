import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Classroom_tests {
    
    private static Student[] _students;
    private static Classroom _classroom;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        _students = new Student[] {
                new Student(1, "Bob", 10),
                new Student(2, "Susan", 9),
                new Student(3, "Jimmy", 11),
                new Student(4, "Alicia", 12)
        };
    }
    
    @BeforeEach
    void setUp() throws Exception {
        _classroom = new Classroom("CompSci Data Structures", 6, 8);
    }

    @Test
    void testConstructor() {
        assertEquals("CompSci Data Structures", _classroom.getName());
        assertEquals(0, _classroom.size());
        assertEquals("Classroom 'CompSci Data Structures' [6 x 8] has 0 students", _classroom.toString());
    }
    
    @Test
    void testGetSetStudent() {
        assertEquals(0, _classroom.size());
        assertFalse(_classroom.setStudent(_students[0], 6, 0));
        assertTrue(_classroom.setStudent(_students[0], 0, 0));
        assertEquals(1, _classroom.size());
        assertEquals(_students[0], _classroom.getStudent(0, 0));
        assertTrue(_classroom.setStudent(_students[3], 5, 7));
        assertEquals(2, _classroom.size());
        assertEquals(_students[3], _classroom.getStudent(5, 7));
    }
}