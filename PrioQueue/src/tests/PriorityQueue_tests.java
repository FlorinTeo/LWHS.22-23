package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import main.HeapPriorityQueue;
import main.PriorityQueue;

class PriorityQueue_tests {

    @Test
    void test_pqStrings() {
        PriorityQueue<String> pq = new HeapPriorityQueue<String>(String.class);
        pq.add("abc");
        assertEquals(1,  pq.size());
        pq.add("xyz");
        assertFalse(pq.isEmpty());
        pq.add("def");
        assertEquals("abc", pq.remove());
        assertEquals("def", pq.remove());
        assertEquals("xyz", pq.remove());
    }
    
    @Test
    void test_pqIntegers() {
        PriorityQueue<Integer> pq = new HeapPriorityQueue<Integer>(Integer.class);
        pq.add(9);
        pq.add(23);
        pq.add(8);
        pq.add(-3);
        pq.add(49);
        pq.add(12);
        assertEquals(6, pq.size());
        assertEquals(-3, pq.peek());
    }
}
