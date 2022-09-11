import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Classroom_tests {
    
    // an array of Student instances to be used during all tests.
    private static Student[] _students;
    
    // the Classroom instance to be setup ahead of each test.
    private static Classroom _classroom;

    /**
     * Creates the test students, once, ahead of executing all the tests.
     * @throws Exception
     */
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        _students = new Student[] {
                new Student(1, "Bob", 10),
                new Student(2, "Susan", 9),
                new Student(3, "Jimmy", 11),
                new Student(4, "Alicia", 12)
        };
    }
    
    /**
     * Creates the Classroom instance ahead of running every test.
     * @throws Exception
     */
    @BeforeEach
    void setUp() throws Exception {
        _classroom = new Classroom("CompSci Data Structures", 4, 3);
    }

    /**
     * Tests the constructor and basic methods of a classroom
     */
    @Test
    void testConstructor() {
        assertEquals("CompSci Data Structures", _classroom.getName());
        assertEquals(0, _classroom.size());
        assertEquals("Classroom 'CompSci Data Structures' [4 x 3] has 0 students", _classroom.toString());
    }
    
    /**
     * Tests the Student accessor and mutator of the classroom.
     */
    @Test
    void testGetSetStudent() {
        // initially the classroom has 0 students.
        assertEquals(0, _classroom.size());
        // Seating a student out of bounds of the classroom is expected to fail.
        assertFalse(_classroom.setStudent(_students[0], 4, 0));
        // Seating a student on a valid seat is expected to succeed.
        assertTrue(_classroom.setStudent(_students[0], 0, 0));
        // Now the classroom should have one student.
        assertEquals(1, _classroom.size());
        // And should be the first student, seated in the first seat.
        assertEquals(_students[0], _classroom.getStudent(0, 0));
        // Seating another student on the last seat in the class should succeed.
        assertTrue(_classroom.setStudent(_students[3], 3, 2));
        // Now there are two students in the classroom.
        assertEquals(2, _classroom.size());
        // Last student is seated on the last seat of the classroom.
        assertEquals(_students[3], _classroom.getStudent(3, 2));
    }
    
    /**
     * Tests adding students as a batch.
     */
    @Test
    void testAddStudents() {
        // classroom is initially empty
        assertEquals(0, _classroom.size());
        // add all 4 students then verify the classroom size and
        // that the first and last students are seated where expected.
        assertTrue(_classroom.addStudents(_students));
        assertEquals(4, _classroom.size());
        assertEquals(_students[0], _classroom.getStudent(0, 0));
        assertEquals(_students[3], _classroom.getStudent(1, 0));
        
        // free up (unseat students from) one of the seats, then
        // add again the 4 students verifying that the freed seat is used
        // as expected by the first student in the batch.
        assertTrue(_classroom.setStudent(null, 0, 1));
        assertEquals(3, _classroom.size());
        assertTrue(_classroom.addStudents(_students));
        assertEquals(7, _classroom.size());
        assertEquals(_students[0], _classroom.getStudent(0, 1));
        assertEquals(_students[3], _classroom.getStudent(2, 0));
    }
}