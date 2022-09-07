/**
 * Class representing a record of a highschool student
 */
public class Student {
    
    // Student ID as given by the school
    private int id;
    
    // Student name
    private String name;
    
    // Student grade: one of 9, 10, 11 or 12.
    private int grade;
    
    /**
     * Creates and initializes an instance of a student.
     * @param id - student ID as given by school.
     * @param name - student first name.
     * @param grade - the grade the student is in (9, 10, 11 or 12).
     */
    public Student(int id, String name, int grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }
    
    /**
     * Class accessor for the ID.
     * @return student's ID.
     */
    public int getId() {
        return id;
    }
    
    /**
     * Class accessor for the name.
     * @return student name.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Class accessor for the grade.
     * @return The grade this student is in.
     */
    public int getGrade() {
        return grade;
    }
    
    /**
     * Class mutator allowing adjustments of the student's name.
     * @param name - The new name for this student.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Gives the string representation of this student.
     */
    @Override
    public String toString() {
        String output = "[" + id + "]";
        output += " " + name;
        output += " in grade " + grade;
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
        if (id <= 0) {
            return false;
        }
        if (name.isBlank() || name.isEmpty()) {
            return false;
        }
        if (grade < 9 || grade > 12) {
            return false;
        }
        
        return true;
    }
}
