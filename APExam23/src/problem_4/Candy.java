package problem_4;

public class Candy {

    /** Returns a String representing the flavor of this piece of candy */
    public String getFlavor() {
        /* implementation below is not shown in exam text */
        return _flavor;
    }
    
    // Instance variables, constructors and methods below are not shown in the exam text.
    
    private String _flavor;
    
    public Candy(String flavor) {
        _flavor = flavor;
    }
    
    @Override
    public String toString() {
        return _flavor;
    }
}
