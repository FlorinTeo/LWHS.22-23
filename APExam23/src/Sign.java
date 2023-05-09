import java.util.ArrayList;
import java.util.List;

public class Sign {

    private List<String> text;
    
    public Sign(String message, int width) {
        text = new ArrayList<String>();
        while(message.length() >= width) {
            text.add(message.substring(0, width));
            message = message.substring(width);
        }
        if (message.length() > 0) {
            text.add(message);
        }
    }
    
    public int numberOfLines() {
        return text.size();
    }
    
    public String getLines() {
        if (numberOfLines() == 0) {
            return null;
        }
        
        String output = "";
        for(String line : text) {
            output += ";" + line;
        }
        return output.substring(1);
    }
}
