package tests;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import main.Student;

class Student_tests {

    @Test
    void testConstructor() {
        // Tests a properly created student instance
        // returns the expected string
        Student fiona = new Student(123, "Fiona Fantastic", 9);
        Assert.assertEquals("[123] Fiona Fantastic in grade 9", fiona.toString());
    }
    
    @Test
    void testAccessors() {
        // Tests that a properly created student instance
        // returns the expected values for each of its accessors
        Student mary = new Student(451, "Mary Marvellous", 12);
        Assert.assertEquals(451, mary.getId());
        Assert.assertEquals("Mary Marvellous", mary.getName());
        Assert.assertEquals(12, mary.getGrade());
    }
    
    @Test
    void testMutator() {
        // Tests that a properly created student instance
        // can be modified via its mutator and the modified value is visible via the accessor
        Student brian = new Student(743, "Brian Briliant", 11);
        Assert.assertNotEquals("Brian Brilliant", brian.getName());
        brian.setName("Brian Brilliant");
        Assert.assertEquals("Brian Brilliant", brian.getName());
    }
    
    @Test
    void testIsValid() {
        // Tests a student with an empty or blank name is correctly marked as invalid
        // Once the name is set to something non blank/empty then instance becomes valid
        Student sean = new Student(852, "", 10);
        Assert.assertFalse(sean.isValid());
        sean.setName(" ");
        Assert.assertFalse(sean.isValid());
        sean.setName("Sean Superstar");
        Assert.assertTrue(sean.isValid());

        // Tests a student with a 0 or negative id is invalid
        // A student with an id as small as 1 becomes valid
        Student ulisse = new Student(-1, "Ulisse Unknown", 9);
        Assert.assertFalse(ulisse.isValid());
        ulisse = new Student(0, "Ulisse Unknown", 9);
        Assert.assertFalse(ulisse.isValid());
        ulisse = new Student(1, "Ulisse Unknown", 9);
        Assert.assertTrue(ulisse.isValid());
        
        // Tests only students with a legal grade (9, 10, 11 or 12) are seen as
        // valid, all the rest are properly detected as invalid.
        for (int grade = -10; grade <= 30; grade++) {
            if (grade >= 9 && grade <= 12) {
                Student ines = new Student(311, "Ines InHighschool", grade);
                Assert.assertTrue(ines.isValid());
            } else {
                Student nick = new Student(311, "Nick NotInHighschool", 13);
                Assert.assertFalse(nick.isValid());
            }
        }
    }

}
