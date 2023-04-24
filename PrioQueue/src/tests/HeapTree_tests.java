package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import main.HeapArray;
import main.HeapTree;
import main.PriorityQueue;

class HeapTree_tests {
    @Test
    void test_HeapTreeStrings() {
        PriorityQueue<String> pq = new HeapTree<String>();
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
    void test_HeapTreeIntegers() {
        PriorityQueue<Integer> pq = new HeapTree<Integer>();
        pq.add(6);
        pq.add(50);
        pq.add(11);
        pq.add(25);
        pq.add(42);
        pq.add(20);
        pq.add(104);
        pq.add(76);
        pq.add(19);
        pq.add(55);
        pq.add(88);
        pq.add(2);
        assertEquals(12, pq.size());
        assertEquals(2, pq.remove());
        assertEquals(6, pq.remove());
        assertEquals(11, pq.remove());
        assertEquals(19, pq.remove());
        for (int i = 5; i <= 11; i++) {
            pq.remove();
        }
        assertEquals(104, pq.remove());
    }
}
