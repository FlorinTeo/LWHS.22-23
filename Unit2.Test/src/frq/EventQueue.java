package frq;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class EventQueue {

    // Internal queue of events
    private Queue<Event> _queue;
    
    /**
     * Constructs an EventQueue object containing 
     * the given list of events.
     * @param events - events to be initially 
     *                 placed into the queue.
     */
    public EventQueue(Event... events) {
        _queue = new LinkedList<Event>();
        _queue.addAll(Arrays.asList(events));
    }
    
    /* additional methods are not relevant */
    
    // EndRegion: Additional code
    
    public void insert(Event event) {
        int n = _queue.size();
        while(n > 0 && _queue.peek().delay <= event.delay) {
            Event e = _queue.remove();
            event.delay -= e.delay;
            _queue.add(e);
            n--;
        }
        
        int shift = event.delay;
        if (shift > 0) {
            _queue.add(event);
        }
        
        while(n > 0) {
            Event e = _queue.remove();
            e.delay -= shift;
            shift = 0;
            _queue.add(e);
            n--;
        }
    }
    
    public void insert2(Event event) {
        boolean inserted = false;
        Queue<Event> newQueue = new LinkedList<Event>();
        while(!_queue.isEmpty()) {
            Event e = _queue.remove();
            if (inserted) {
                newQueue.add(e);
            } else if (event.delay > e.delay) {
                event.delay -= e.delay;
                newQueue.add(e);
            } else if (event.delay == e.delay) {
                newQueue.add(e);
                inserted = true;
            } else {
                newQueue.add(event);
                e.delay -= event.delay;
                newQueue.add(e);
                inserted = true;
            }
        }
        
        if (!inserted) {
            newQueue.add(event);
        }
        
        _queue = newQueue;
    }

    @Override
    public String toString() {
        return _queue.toString();
    }
}
