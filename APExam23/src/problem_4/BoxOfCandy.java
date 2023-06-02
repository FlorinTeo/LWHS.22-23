package problem_4;

public class BoxOfCandy {

    /** box contains at least one row and is initialized in the constructor. */
    private Candy[][] box;
    
    /**
     * Moves one piece of candy in column {col}, if necessary and possible, so that the box
     * element in row 0 of column {col} contains a piece of candy, as described in part (a).
     * Returns false if there is no piece of candy in column {col} and returns true otherwise.
     * Precondition: {col} is valid column index in box.
     */
    public boolean moveCandyToFirstRow(int col) {
        /* implemented for part (a) */
        if (box[0][col] != null) {
            return true;
        }
        
        for (int row = 1; row < box.length; row++) {
            if(box[row][col] != null) {
                box[0][col] = box[row][col];
                box[row][col] = null;
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Removes from box and returns a piece of candy with flavor specified by the parameter, or
     * returns null if no such piece is found, as described in part (b).
     */
    public Candy removeNextByFlavor(String flavor) {
        /* implemented for part (b) */
        for(int row = box.length-1; row >= 0; row--) {
            for (int col = 0; col < box[row].length; col++) {
                Candy candy = box[row][col];
                if (candy != null && candy.getFlavor().equals(flavor)) {
                    box[row][col] = null;
                    return candy;
                }
            }
        }
        
        return null;
    }
    
    // Instance variables, constructors and methods below are not shown in the exam text.
    public BoxOfCandy(Candy[][] candies) {
        box = candies;
        box = new Candy[candies.length][candies[0].length];
        for(int row = 0; row < box.length; row++) {
            for (int col = 0; col < box[row].length; col++) {
                box[row][col] = candies[row][col];
            }
        }
    }
    
    public Candy[][] getCandies() {
        Candy[][] candies = new Candy[box.length][box[0].length];
        for(int row = 0; row < candies.length; row++) {
            for (int col = 0; col < candies[row].length; col++) {
                candies[row][col] = box[row][col];
            }
        }
        return candies;
    }
}
