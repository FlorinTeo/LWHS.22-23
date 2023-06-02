package problem_2;
import java.util.ArrayList;

public class Sign {

    // list of lines constructed from the message
    private ArrayList<String> lines;
    
    /**
     * Class constructor takes a {message} and a {width} to be used
     * to break the message in lines of maximum width characters.
     */
    public Sign(String message, int width) {
        lines = new ArrayList<String>();
        while(message.length() >= width) {
            lines.add(message.substring(0, width));
            message = message.substring(width);
        }
        
        if (message.length() > 0) {
            lines.add(message);
        }
    }
    
    /**
     * Returns the number of lines extracted from the initial message
     */
    public int numberOfLines() {
        return lines.size();
    }
    
    /**
     * Returns a string where all lines are joined together, separated
     * by the ';' character. If there are no lines, method returns null.
     */
    public String getLines() {
        if (numberOfLines() == 0) {
            return null;
        }
        
        String output = "";
        for(String line : lines) {
            output += ";" + line;
        }
        
        return output.substring(1);
    }
}
