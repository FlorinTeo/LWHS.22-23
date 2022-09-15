package main;
/**
 * Class representing a record of a highschool student
 */
public class Student {
    
    // Student ID as given by the school
    private int _id;
    
    // Student name
    private String _name;
    
    // Student grade: one of 9, 10, 11 or 12.
    private int _grade;
    
    /**
     * Creates and initializes an instance of a student.
     * @param id - student ID as given by school.
     * @param name - student first name.
     * @param grade - the grade the student is in (9, 10, 11 or 12).
     */
    public Student(int id, String name, int grade) {
        _id = id;
        _name = name;
        _grade = grade;
    }
    
    /**
     * Class accessor for the ID.
     * @return student's ID.
     */
    public int getId() {
        return _id;
    }
    
    /**
     * Class accessor for the name.
     * @return student name.
     */
    public String getName() {
        return _name;
    }
    
    /**
     * Class accessor for the grade.
     * @return The grade this student is in.
     */
    public int getGrade() {
        return _grade;
    }
    
    /**
     * Class mutator allowing adjustments of the student's name.
     * @param name - The new name for this student.
     */
    public void setName(String name) {
        _name = name;
    }
    
    /**
     * Gives the string representation of this student.
     */
    @Override
    public String toString() {
        String output = "[" + _id + "]";
        output += " " + _name;
        output += " in grade " + _grade;
        return output;
    }
    
    /**
     * Check's if this student's instance is valid:
     * id should be a positive number
     * name should be a non-null string
     * grade should be one of the 9, 10, 11 or 12
     * @return - true if instance is valid, false otherwise
     */
    public boolean isValid() {
        if (_id <= 0) {
            return false;
        }
        
        // String class has both isBlank() and isEmpty() methods.
        // isBlank() returns true if string is either empty or has only white chars.
        // isEmpty() returns true only if string is empty.
        // For us is sufficient to use the encommpassing isBlank()
        if (_name == null || _name.trim().isEmpty()) {
            return false;
        }
        
        if (_grade < 9 || _grade > 12) {
            return false;
        }
        
        return true;
    }
}
