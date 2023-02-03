package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.Assert;

import frq.Event;
import frq.EventQueue;

class EventQueue_tests {

    private EventQueue tq;
    
    @BeforeEach
    void setUp() throws Exception {
        tq = new EventQueue(
                new Event("first", 10),
                new Event("second", 10),
                new Event("third", 10));
    }

    @Test
    void test_insertEmpty() {
        tq = new EventQueue();
        tq.insert(new Event("test", 5));
        Assert.assertEquals("[test:5]", tq.toString());
    }
    
    @Test
    void test_insertFirst() {
        Assert.assertEquals("[first:10, second:10, third:10]", tq.toString());
        tq.insert(new Event("test", 8));
        Assert.assertEquals("[test:8, first:2, second:10, third:10]", tq.toString());
    }
    
    @Test
    void test_insertMiddle() {
        Assert.assertEquals("[first:10, second:10, third:10]", tq.toString());
        tq.insert(new Event("test", 12));
        Assert.assertEquals("[first:10, test:2, second:8, third:10]", tq.toString());
    }
    
    @Test
    void test_insertEnd() {
        Assert.assertEquals("[first:10, second:10, third:10]", tq.toString());
        tq.insert(new Event("test", 35));
        Assert.assertEquals("[first:10, second:10, third:10, test:5]", tq.toString());
    }
    
    @Test
    void test_insertMatching() {
        Assert.assertEquals("[first:10, second:10, third:10]", tq.toString());
        tq.insert(new Event("test", 20));
        Assert.assertEquals("[first:10, second:10, third:10]", tq.toString());
    }

}
