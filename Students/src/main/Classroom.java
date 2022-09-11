package main;

public class Classroom {
    // The name of this classroom
    String _name;
    
    // The seating layout in the classroom as a two-dimensional grid of seats 
    private Student[][] _seating;
    
    /**
     * Creates a classroom with a given name and seating layout.
     * Initially the classroom has no students assigned to any seat.
     * @param name - classroom name.
     * @param rows - number of rows in the seating layout.
     * @param cols - number of columns in the seating layout.
     */
    public Classroom(String name, int rows, int cols) {
        _name = name;
        _seating = new Student[rows][cols];
    }
    
    /**
     * Gives the classroom name.
     * @return - classroom name.
     */
    public String getName() {
        return _name;
    }
    
    /**
     * Returns the student on the given seat in the classroom.
     * If the seat doesn't exist or is unoccupied, returns null.
     * @param row - the row of the student's seat
     * @param col - the column of the student's seat
     * @return - the student object, or null if seat is out of bounds or is unoccupied.
     */
    public Student getStudent(int row, int col) {
        if (row < _seating.length && col < _seating[row].length) {
            return _seating[row][col];
        } else {
            return null;
        }
    }
    
    /**
     * Assigns the given student to the given seat in the classroom.
     * Returns success or failure if the seat doesn't exist in the classroom.
     * @param student - the student to be assigned.
     * @param row - the row where the student should sit.
     * @param col - the column where the student should sit.
     * @return - true if successful, false if the seat is out of bounds.
     */
    public boolean setStudent(Student student, int row, int col) {
        if (row < _seating.length && col < _seating[row].length) {
            _seating[row][col] = student;
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Returns the number of students currently assigned seats in this classroom.
     * @return - number of students in the classroom.
     */
    public int size() {
        int count = 0;
        for (int r = 0; r < _seating.length; r++) {
            for (int c = 0; c < _seating[r].length; c++) {
                if (_seating[r][c] != null) {
                    count++;
                }
            }
        }
        
        return count;
    }
    
    /**
     * Adds a bulk of students to any available seat in the classroom.
     * Returns success, or failure if there are no sufficient seats for all students.
     * In the latter case, class is filled up before returning failure.
     * @param students - bulk of students to be seated.
     * @return - success or failure if class is full and cannot accomodate all students.
     */
    public boolean addStudents(Student... students) {
        int i = 0;
        for (int r = 0; r < _seating.length && i < students.length; r++) {
            for (int c = 0; c < _seating[r].length && i < students.length; c++) {
                if (_seating[r][c] == null) {
                    _seating[r][c] = students[i];
                    i++;
                }
            }
        }
        
        return (i == students.length);
    }
    
    /**
     * Gives the string representation of this object.
     */
    @Override
    public String toString() {
        String output = "Classroom '" + _name + "'";
        output += " [" + _seating.length + " x " + _seating[0].length + "]";
        output += " has " + size() + " students";
        return output;
    }
}
