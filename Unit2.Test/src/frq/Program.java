package frq;

public class Program {

    public static void main(String[] args) {
        // { event_A:10, event_B:6, event_C:12, event_D:8}
        EventQueue eventQueue = new EventQueue(
                new Event("event_A", 10),
                new Event("event_B", 6),
                new Event("event_C", 12),
                new Event("event_D", 8));
        System.out.println(eventQueue);
        Event e = new Event("event_X", 23);
        eventQueue.insert(e);
        
        System.out.println(eventQueue);
    }

}
