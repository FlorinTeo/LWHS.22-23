package frq;

public class Event {
    public String name;
    public int delay;
    
    public Event(String name, int delay) {
        this.name = name;
        this.delay = delay;
    }
    
    @Override
    public String toString() {
        return name + ":" + delay;
    }
}
